package cn.edu.ntu.seckill.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-17 21:31 <br>
 * @project project-seckill <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class SeckillGoodsBO extends BaseBO {

  @NotNull private String goodsId;

  @Min(0)
  @NotNull
  private BigDecimal seckillPrice;

  @Min(0)
  @NotNull
  private Integer stock;

  @NotNull private LocalDateTime startDate;
  @NotNull private LocalDateTime endDate;
}
