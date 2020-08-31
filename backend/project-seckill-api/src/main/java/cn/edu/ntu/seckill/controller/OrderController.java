package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.OrderApi;
import cn.edu.ntu.seckill.component.RedisTemplateUtils;
import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.model.bo.UserBO;
import cn.edu.ntu.seckill.mq.MqProducer;
import cn.edu.ntu.seckill.redis.RedisCommonEnum;
import cn.edu.ntu.seckill.redis.RedisSeckillGoodsKeyEnum;
import cn.edu.ntu.seckill.service.ISeckillGoodsService;
import cn.edu.ntu.seckill.service.IStockLogService;
import cn.edu.ntu.seckill.service.IStockService;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author zack <br>
 * @create 2020-08-29 20:51 <br>
 * @project project-seckill <br>
 */
@Slf4j
@OrderApi
@RestController
public class OrderController extends BaseController {

  @Resource private MqProducer mqProducer;
  @Resource private RedisTemplateUtils redisTemplateUtils;
  @Resource ISeckillGoodsService seckillGoodsService;
  @Resource IStockLogService stockLogService;
  @Resource private IStockService stockService;

  private ExecutorService executorService;
  private RateLimiter orderCreateRateLimiter;

  @PostConstruct
  public void init() {
    executorService = newFixedThreadPool(20);
    orderCreateRateLimiter = RateLimiter.create(300);
  }

  @GetMapping(value = "/order/generate-verify-code")
  @ResponseBody
  public void generateVerifyCode(@ApiIgnore UserBO user, HttpServletResponse response)
      throws IOException {

    CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 10);
    redisTemplateUtils.set(
        captcha.getCode(), 60, TimeUnit.SECONDS, RedisCommonEnum.VERIFY_CODE, user.getId());

    log.debug("captcha code(): {}", captcha.getCode());
    ImageIO.write(captcha.getImage(), "jpeg", response.getOutputStream());
  }

  @GetMapping(value = "/order/generate-token")
  @ResponseBody
  public JSON generateToken(
      @RequestParam(name = "goodsId") String goodsId,
      @RequestParam(name = "seckillGoodsId") String seckillGoodsId,
      @RequestParam(name = "verifyCode") String verifyCode,
      @ApiIgnore UserBO user)
      throws BusinessException {

    String captchaCode =
        redisTemplateUtils.get(String.class, RedisCommonEnum.VERIFY_CODE, user.getId());
    if (StrUtil.isBlank(captchaCode) || !StrUtil.equalsIgnoreCase(captchaCode, verifyCode)) {
      throw new BusinessException().new CaptchaInvalidException(verifyCode);
    }

    String seckillToken = seckillGoodsService.generateToken(seckillGoodsId, goodsId, user);
    if (seckillToken == null) {
      throw new BusinessException().new GenerateTokenException();
    }

    log.debug("seckill token: {}", seckillToken);
    return buildJson("seckillToken", seckillToken);
  }

  /**
   * decrease goods stock decrease seckill goods stock create stock log create order create seckill
   * order
   *
   * @param goodsId
   * @param amount
   * @param seckillGoodsId
   * @param seckillToken
   * @param user
   * @return
   */
  @PostMapping("/orders")
  public JSON create(
      @RequestParam(name = "goodsId") String goodsId,
      @RequestParam(name = "amount") Integer amount,
      @RequestParam(name = "seckillGoodsId", required = false) String seckillGoodsId,
      @RequestParam(name = "seckillToken", required = false) String seckillToken,
      @ApiIgnore UserBO user) {

    // rate limit check
    if (!orderCreateRateLimiter.tryAcquire()) {
      throw new BusinessException().new RateLimitException();
    }

    // validate seckill-token
    if (seckillGoodsId != null) {
      String seckillTokenFromCache =
          redisTemplateUtils.get(
              String.class,
              RedisSeckillGoodsKeyEnum.SECKILL_GOODS_TOKEN,
              seckillGoodsId,
              user.getId());
      if (StrUtil.isBlank(seckillTokenFromCache)
          || !StrUtil.equalsIgnoreCase(seckillTokenFromCache, seckillToken)) {
        throw new BusinessException("Validate seckill-token error");
      }
    }

    // 同步调用线程池的 submit 方法
    // 拥塞窗口为20的等待队列, 用来队列化泄洪
    Future<Object> future =
        executorService.submit(
            () -> {
              String stockLogId = stockLogService.initStockLog(goodsId, seckillGoodsId, amount);
              // send reduce stock message asynchronously
              boolean asyncReduceStock =
                  mqProducer.reduceStockTransactionAsync(
                      user.getId(), goodsId, seckillGoodsId, amount, stockLogId);
              if (!asyncReduceStock) {
                if (StrUtil.isNotBlank(seckillGoodsId)) {
                  // increase redis stock due to create order failed
                  stockService.increaseStockInCache(seckillGoodsId, amount);
                }

                throw new BusinessException("create order error");
              }

              return null;
            });

    try {
      // block to wait execute finish
      future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new BusinessException("create order error");
    }

    return null;
  }
}
