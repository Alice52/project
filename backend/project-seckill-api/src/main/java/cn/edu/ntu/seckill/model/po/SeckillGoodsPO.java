package cn.edu.ntu.seckill.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-11 21:58 <br>
 * @project project-seckill <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeckillGoodsPO extends BasePO {
  private String goodsId;
  private BigDecimal seckillPrice;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
