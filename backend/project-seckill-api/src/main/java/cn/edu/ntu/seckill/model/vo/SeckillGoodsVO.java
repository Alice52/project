package cn.edu.ntu.seckill.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-17 21:33 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SeckillGoodsVO extends BaseVO {

  @NotNull private String goodsId;
  @NotNull private BigDecimal seckillPrice;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @NotNull
  private LocalDateTime endDate;
}
