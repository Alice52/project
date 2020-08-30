package cn.edu.ntu.seckill.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-30 14:05 <br>
 * @project project-seckill <br>
 */
@Validated
public interface IStockService {
  /**
   * Decrease stock of seckill goods asynchronously.
   *
   * @param seckillGoodsId
   * @param amount
   * @return
   */
  boolean decreaseStockInCache(@NotBlank String seckillGoodsId, @NotNull Integer amount);

  /**
   * increase stock inn cache.
   *
   * @param seckillGoodsId
   * @param amount
   * @return
   */
  boolean increaseStockInCache(@NotBlank String seckillGoodsId, @NotNull Integer amount);

  /**
   * reduce stock.
   *
   * @param goodsId
   * @param seckillGoodsId
   * @param amount
   */
  @Transactional(
      propagation = Propagation.REQUIRED,
      isolation = Isolation.READ_COMMITTED,
      rollbackFor = {Exception.class})
  void decreaseStock(@NotBlank String goodsId, String seckillGoodsId, @Min(1) Integer amount);

  /**
   * 非事务的减库存.
   *
   * @param goodsId
   * @param seckillGoodsId
   * @param amount
   * @return
   */
  @Deprecated
  boolean asyncDecreaseStock(String goodsId, String seckillGoodsId, Integer amount);
}
