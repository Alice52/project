package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.model.bo.StockLogBO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * These api should be use `REQUIRES_NEW` due to it's log data.
 *
 * @author zack <br>
 * @create 2020-08-30 12:38 <br>
 * @project project-seckill <br>
 */
@Validated
public interface IStockLogService {
  /**
   * Init stock log.
   *
   * @param goodsId
   * @param seckillGoodsId
   * @param amount
   * @return
   */
  @Transactional(
      propagation = Propagation.REQUIRES_NEW,
      isolation = Isolation.READ_COMMITTED,
      rollbackFor = {Exception.class})
  String initStockLog(@NotBlank String goodsId, String seckillGoodsId, @Min(1) Integer amount);

  /**
   * Update stock log status to specified status.
   *
   * @param stockLogId
   * @param status
   */
  @Transactional(
      propagation = Propagation.REQUIRES_NEW,
      isolation = Isolation.READ_COMMITTED,
      rollbackFor = {Exception.class})
  void updateStatus(String stockLogId, Integer status);

  /**
   * Get by id.
   *
   * @param id
   * @return
   */
  StockLogBO getByPK(@NotBlank String id);
}
