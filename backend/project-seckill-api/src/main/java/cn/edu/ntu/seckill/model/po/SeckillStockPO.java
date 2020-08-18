package cn.edu.ntu.seckill.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-11 22:00 <br>
 * @project project-seckill <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeckillStockPO extends BasePO {
  @NotBlank private String goodsId;

  @NotNull
  @Min(0)
  private Integer stock;
}
