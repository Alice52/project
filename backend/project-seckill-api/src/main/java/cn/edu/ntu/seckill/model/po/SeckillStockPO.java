package cn.edu.ntu.seckill.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2020-08-11 22:00 <br>
 * @project project-seckill <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeckillStockPO extends BasePO {
  private String seckillGoodsId;
  private Integer seckillGoodsStock;
}
