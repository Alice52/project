package ec.product.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zack <br>
 * @create 2020/12/12 <br>
 * @project project-ec <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvicedAttrGroupVO {
  @JsonProperty("group")
  public AttrGroupVO groupVO;

  @JsonProperty("attrs")
  public List<AttrVO> attrVOS;
}
