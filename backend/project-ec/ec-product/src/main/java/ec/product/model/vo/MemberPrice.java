package ec.product.model.vo;

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
public class MemberPrice {
  private Long id;
  private String name;
  private BigDecimal price;
}
