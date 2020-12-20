package ec.ware.model.vo;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020/12/20 <br>
 * @project project-ec <br>
 */
@Data
public class PurchaseMergeVO {
  private Long purchaseId;

  @Size(min = 1)
  private List<Long> items;
}
