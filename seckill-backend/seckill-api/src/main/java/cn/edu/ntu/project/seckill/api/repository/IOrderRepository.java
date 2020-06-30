package cn.edu.ntu.project.seckill.api.repository;

import cn.edu.ntu.project.seckill.api.entities.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @author zack <br>
 * @create 2020-06-30 22:43 <br>
 * @project seckill-backend <br>
 */
@Mapper
public interface IOrderRepository {

  /**
   * Create order.
   *
   * @param order
   * @return
   */
  @Insert(
      "INSERT INTO `seckill.order` (id, user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)"
          + "VALUES(#{id}, #{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{orderStatus},#{createAt} )")
//  @SelectKey(
//      keyColumn = "id",
//      keyProperty = "id",
//      resultType = long.class,
//      before = false,
//      statement = "select last_insert_id()")
  long insert(Order order);

  /** Delete orders. */
  @Delete("DELETE FROM `seckill.order`")
  void deleteOrders();
}
