package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.bo.OrderBO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zack <br>
 * @create 2020-08-30 11:26 <br>
 * @project project-seckill <br>
 */
@Mapper
public interface ISeckillGoodsOrderRepository {

  /**
   * Create seckill goods order.
   *
   * @param order
   */
  void insertSelective(OrderBO order);
}
