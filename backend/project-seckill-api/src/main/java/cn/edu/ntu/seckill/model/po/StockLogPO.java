package cn.edu.ntu.seckill.model.po;

import lombok.*;

/**
 * @author zack <br>
 * @create 2020-08-30 12:36 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockLogPO extends BasePO {
  private String goodsId;
  private String seckillGoodsId;
  private Integer amount;
  /**
   * 1: initial <br>
   * 2: success <br>
   * 3: rollback <br>
   */
  private Integer status;
}
