package ec.ware.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品库存
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Data
@JsonIgnoreProperties(
    value = {"createdDate", "updatedDate", "isDeleted"},
    allowGetters = true)
public class WareSkuVO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(hidden = true)
  private Long id;

  private Long skuId;
  private Long wareId;
  private Integer stock;
  private String skuName;
  private Integer stockLocked;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;
}
