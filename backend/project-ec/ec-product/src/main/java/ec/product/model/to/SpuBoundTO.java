package ec.product.model.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpuBoundTO {
  private Long spuId;
  private BigDecimal buyBounds;
  private BigDecimal growBounds;
}
