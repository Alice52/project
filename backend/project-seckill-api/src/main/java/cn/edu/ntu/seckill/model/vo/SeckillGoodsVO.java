package cn.edu.ntu.seckill.model.vo;

import cn.edu.ntu.seckill.enumeration.SeckillStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
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

  @ApiModelProperty(hidden = true)
  private SeckillStatusEnum status;

  @NotNull private String goodsId;

  @Min(0)
  @NotNull
  private BigDecimal seckillPrice;

  @Min(0)
  @NotNull
  private Integer stock;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime startDate;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime endDate;
}
