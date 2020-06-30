package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.entities.SecKillOrder;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;

/**
 * @author zack <br>
 * @create 2020-06-30 21:12 <br>
 * @project seckill-backend <br>
 */
public interface IOrderService {

  /** Delete all orders. */
  void deleteOrders();

  /**
   * getSecKillOrderByUserIdGoodsId
   *
   * @param username
   * @param goodsId
   * @return SeckillOrder
   */
  SecKillOrder getSecKillOrderByUserIdGoodsId(String username, String goodsId);

  /**
   * Create order and seckill order.
   *
   * @param user
   * @param goods
   * @return
   */
  SecKillOrder createOrder(SeckillUser user, GoodsVo goods);
}
