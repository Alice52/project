package cn.edu.ntu.seckill.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author zack <br>
 * @create 2020-08-11 21:47 <br>
 * @project project-seckill <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsPO extends BasePO {

  /** Add unique index. */
  @NotBlank private String name;

  @NotBlank private String title;
  @NotBlank private String img;
  @NotBlank private String detail;

  @Min(0)
  @NotNull
  private BigDecimal price;

  @Min(0)
  @NotNull
  private Integer stock;
}
