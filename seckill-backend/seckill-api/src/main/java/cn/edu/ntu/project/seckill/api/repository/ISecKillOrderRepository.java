package cn.edu.ntu.project.seckill.api.repository;

import cn.edu.ntu.project.seckill.api.entities.SecKillOrder;
import org.apache.ibatis.annotations.*;

/**
 * @author zack <br>
 * @create 2020-06-30 21:13 <br>
 * @project seckill-backend <br>
 */
@Mapper
public interface ISecKillOrderRepository {

  /** Delete all orders. */
  @Delete("DELETE FROM `seckill.seckill_order`")
  void deleteOrders();

  /**
   * Get by condition: username, goodsId
   *
   * @param userName
   * @param goodsId
   * @return SeckillOrder
   */
  @Select(
      "SELECT order_id, user_id, goods_id FROM `seckill.seckill_order` WHERE user_id=#{userId} and goods_id=#{goodsId}")
  SecKillOrder getByCondition(@Param("userId") String userName, @Param("goodsId") String goodsId);

  /**
   * Insert order.
   *
   * @param seckillOrder
   * @return int
   */
  @Insert(
      "INSERT INTO `seckill.seckill_order` (id, user_id, goods_id, order_id) VALUES(#{id}, #{userId}, #{goodsId}, #{orderId})")
  int insertSecKillOrder(SecKillOrder seckillOrder);
}
