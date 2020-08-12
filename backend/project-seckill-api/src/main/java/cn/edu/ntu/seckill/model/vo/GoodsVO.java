package cn.edu.ntu.seckill.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author zack <br>
 * @create 2020-08-11 22:08 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVO {

  @ApiModelProperty(hidden = true)
  private String id;

  @NotBlank private String name;

  @NotBlank private String title;

  @NotBlank private String image;

  @NotBlank private String detail;

  @NotNull
  @Min(0)
  private BigDecimal price;

  @NotNull
  @Min(0)
  private Integer stock;
}
