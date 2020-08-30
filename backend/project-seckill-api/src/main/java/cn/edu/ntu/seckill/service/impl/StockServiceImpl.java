package cn.edu.ntu.seckill.service.impl;

import cn.edu.ntu.seckill.component.RedisTemplateUtils;
import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.mq.MqProducer;
import cn.edu.ntu.seckill.redis.RedisSeckillGoodsKeyEnum;
import cn.edu.ntu.seckill.service.IGoodsService;
import cn.edu.ntu.seckill.service.ISeckillGoodsService;
import cn.edu.ntu.seckill.service.IStockService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.swing.StringUIClientPropertyKey;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020-08-30 14:07 <br>
 * @project project-seckill <br>
 */
@Service
@Slf4j
public class StockServiceImpl implements IStockService {
  @Resource private MqProducer mqProducer;
  @Resource private ISeckillGoodsService seckillGoodsService;
  @Resource private IGoodsService goodsService;

  @Resource private RedisTemplateUtils redisTemplateUtils;

  @Override
  public boolean asyncDecreaseStock(String goodsId, String seckillGoodsId, Integer amount) {
    boolean mqResult = mqProducer.asyncReduceStock(goodsId, seckillGoodsId, amount);
    // add in redis if mqResult is false
    return mqResult;
  }

  @Override
  public boolean decreaseStockInCache(String seckillGoodsId, Integer amount) {
    // TODO: can reduce in memory first.
    long result =
        redisTemplateUtils.increment(
            -1 * amount, RedisSeckillGoodsKeyEnum.SECKILL_GOODS_STOCK, seckillGoodsId);
    if (result < 0) {
      log.warn("seckill goods is sold out: {}", seckillGoodsId);
      return false;
    }

    if (result > 0) {
      return true;
    } else if (result == 0) {
      redisTemplateUtils.set(true, RedisSeckillGoodsKeyEnum.SECKILL_GOODS_OVER, seckillGoodsId);
      return true;
    } else {
      increaseStockInCache(seckillGoodsId, amount);
      return false;
    }
  }

  @Override
  public boolean increaseStockInCache(String seckillGoodsId, Integer amount) {

    redisTemplateUtils.increment(
        amount, RedisSeckillGoodsKeyEnum.SECKILL_GOODS_STOCK, seckillGoodsId);
    return true;
  }

  @Override
  public void decreaseStock(
      @NotBlank String goodsId, String seckillGoodsId, @Min(1) Integer amount) {

    goodsService.decreaseStock(goodsId, amount);

    if (StrUtil.isNotBlank(seckillGoodsId)) {
      seckillGoodsService.decreaseStock(seckillGoodsId, amount);
    }
  }
}
