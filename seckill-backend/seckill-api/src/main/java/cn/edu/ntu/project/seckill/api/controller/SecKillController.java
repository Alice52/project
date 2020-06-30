package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.api.annotation.AccessLimit;
import cn.edu.ntu.project.seckill.api.configuration.RedisGoodKeyEnum;
import cn.edu.ntu.project.seckill.api.configuration.RedisOrderKeyEnum;
import cn.edu.ntu.project.seckill.api.entities.SecKillOrder;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.exception.SecKillException;
import cn.edu.ntu.project.seckill.api.exception.UserException;
import cn.edu.ntu.project.seckill.api.mq.MqSender;
import cn.edu.ntu.project.seckill.api.mq.SecKillMessage;
import cn.edu.ntu.project.seckill.api.service.IGoodsService;
import cn.edu.ntu.project.seckill.api.service.IOrderService;
import cn.edu.ntu.project.seckill.api.service.SecKillService;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-30 20:43 <br>
 * @project seckill-backend <br>
 */
@RestController
@Api
public class SecKillController implements InitializingBean {

  private HashMap<String, Boolean> localOverMap = new HashMap<>();
  @Resource IGoodsService goodsService;
  @Resource RedisService redisService;
  @Resource SecKillService secKillService;
  @Resource IOrderService orderService;
  @Resource MqSender sender;

  /**
   * Init this system data in redis and memory.
   *
   * @throws Exception
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    List<GoodsVo> goodsList = goodsService.list();

    if (goodsList == null) {
      return;
    }

    for (GoodsVo goods : goodsList) {
      redisService.set(RedisGoodKeyEnum.GOODS_STOCK, goods.getId(), goods.getStockCount());
      localOverMap.put(goods.getId(), false);
    }
  }

  @GetMapping(value = "/goods/reset")
  @ResponseBody
  public Boolean reset() {
    List<GoodsVo> goodsList = goodsService.list();

    for (GoodsVo goods : goodsList) {
      goods.setStockCount(10);
      redisService.set(RedisGoodKeyEnum.GOODS_STOCK, goods.getId(), goods.getStockCount());
      localOverMap.put(goods.getId(), false);
    }

    redisService.delete(RedisOrderKeyEnum.ORDER_USER);
    redisService.delete(RedisGoodKeyEnum.GOODS_OVER);
    secKillService.reset(goodsList);
    return true;
  }

  @AccessLimit
  @PostMapping(value = "/goods/sec-kill")
  @ResponseBody
  public String secKill(SeckillUser user, @RequestParam("goodsId") String goodsId) {

    if (user == null) {
      throw new UserException().new UserLoginException();
    }

    // 内存标记, 减少redis访问
    boolean over = localOverMap.get(goodsId);
    if (over) {
      throw new SecKillException().new SecKillGoodsOverException();
    }

    // 预减库存
    long stock = redisService.decr(RedisGoodKeyEnum.GOODS_STOCK, goodsId);
    if (stock < 0) {
      localOverMap.put(goodsId, true);
      throw new SecKillException().new SecKillGoodsOverException();
    }
    // 判断是否已经秒杀到了
    SecKillOrder order = orderService.getSecKillOrderByUserIdGoodsId(user.getNickname(), goodsId);
    if (order != null) {
      throw new SecKillException().new RepeatedSecKillException();
    }

    // 入队
    SecKillMessage sm = new SecKillMessage();
    sm.setUser(user);
    sm.setGoodsId(goodsId);
    sender.sendSecKillMessage(sm);
    // 排队中
    return "Queued";
  }

  /** orderId：成功 -1：秒杀失败 0： 排队中 */
  @GetMapping(value = "/sec-kill/result")
  public String secKillResult(SeckillUser user, @RequestParam("goodsId") String goodsId) {
    if (user == null) {
      throw new UserException().new UserLoginException();
    }
    String result = secKillService.getSecKillResult(user.getNickname(), goodsId);
    return result;
  }
}
