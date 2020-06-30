package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.configuration.RedisGoodKeyEnum;
import cn.edu.ntu.project.seckill.api.entities.SecKillOrder;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.exception.SecKillException;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-30 21:03 <br>
 * @project seckill-backend <br>
 */
@Service
public class SecKillService implements ISecKillService {

  @Resource IGoodsService goodsService;
  @Resource IOrderService orderService;
  @Resource RedisService redisService;

  @Override
  public void reset(List<GoodsVo> goodsList) {
    goodsService.resetStock(goodsList);
    orderService.deleteOrders();
  }

  @Override
  @Transactional
  public SecKillOrder secKill(SeckillUser user, GoodsVo goods) {
    // 减库存 下订单 写入秒杀订单
    boolean success = goodsService.reduceStock(goods);
    if (success) {
      return orderService.createOrder(user, goods);
    } else {
      setGoodsOver(goods.getId());
      throw new SecKillException().new SecKillGoodsOverException();
    }
  }

  @Override
  public String getSecKillResult(String userId, String goodsId) {
    SecKillOrder order = orderService.getSecKillOrderByUserIdGoodsId(userId, goodsId);
    // 秒杀成功
    if (order != null) {
      return order.getOrderId();
    } else {
      boolean isOver = getGoodsOver(goodsId);
      if (isOver) {
        return "Failed";
      } else {
        return "Queued";
      }
    }
  }

  private boolean getGoodsOver(String goodsId) {
    return redisService.exists(RedisGoodKeyEnum.GOODS_OVER, "" + goodsId);
  }

  private void setGoodsOver(String goodsId) {
    redisService.set(RedisGoodKeyEnum.GOODS_OVER, "" + goodsId, true);
  }
}
