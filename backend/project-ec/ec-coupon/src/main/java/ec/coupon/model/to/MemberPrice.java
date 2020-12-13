package ec.coupon.model.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Data
public class MemberPrice {
  private Long id;
  private String name;
  private BigDecimal price;
}
