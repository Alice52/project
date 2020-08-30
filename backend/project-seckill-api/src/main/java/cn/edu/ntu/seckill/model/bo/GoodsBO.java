package cn.edu.ntu.seckill.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author zack <br>
 * @create 2020-08-11 22:14 <br>
 * @project project-seckill <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsBO extends BaseBO {

  @NotBlank private String name;
  @NotBlank private String title;
  @NotBlank private String image;
  @NotBlank private String detail;

  @Min(0)
  private BigDecimal price;

  @Min(0)
  private Integer stock;

  public GoodsBO(String id) {
    super(id);
  }
}
