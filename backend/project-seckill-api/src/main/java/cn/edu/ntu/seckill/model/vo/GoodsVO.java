package cn.edu.ntu.seckill.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

  private String id;

  @ApiModelProperty(required = true)
  @NotBlank
  private String name;

  @ApiModelProperty(required = true)
  @NotBlank
  private String title;

  @ApiModelProperty(required = true)
  @NotBlank
  private String image;

  @ApiModelProperty(required = true)
  @NotBlank
  private String detail;

  @ApiModelProperty(required = true)
  @Min(0)
  private BigDecimal price;

  @ApiModelProperty(required = true)
  @Min(0)
  private Integer stock;
}
