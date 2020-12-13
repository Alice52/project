package ec.product.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Data
@NoArgsConstructor
public class BaseAttrs {
  private Long attrId;
  private String attrValues;
  private int showDesc;
}
