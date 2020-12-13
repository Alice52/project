package ec.product.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Data
public class SpuSaveVO {

  private String spuName;
  private String spuDescription;
  private Long catalogId;
  private Long brandId;
  private BigDecimal weight;
  private Integer publishStatus;
  private List<String> decript;
  private List<String> images;
  private Bounds bounds;
  private List<BaseAttrs> baseAttrs;
  private List<Skus> skus;
}
