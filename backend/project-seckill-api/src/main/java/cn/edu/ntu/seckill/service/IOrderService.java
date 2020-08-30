package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.model.bo.OrderBO;
import cn.edu.ntu.seckill.model.bo.UserBO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-30 10:40 <br>
 * @project project-seckill <br>
 */
@Validated
public interface IOrderService {

  /**
   * Create order.
   *
   * @param userId
   * @param goodsId
   * @param seckillGoodsId
   * @param amount
   * @param stockLogId
   * @return
   * @throws BusinessException
   */
  @Transactional(
      propagation = Propagation.REQUIRED,
      isolation = Isolation.READ_COMMITTED,
      rollbackFor = {Exception.class})
  OrderBO createOrder(
      String userId,
      @NotBlank String goodsId,
      String seckillGoodsId,
      @NotNull Integer amount,
      @NotBlank String stockLogId);
}
