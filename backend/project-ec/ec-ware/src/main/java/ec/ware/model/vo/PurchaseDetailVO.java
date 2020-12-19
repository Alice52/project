package ec.ware.model.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Data
@JsonIgnoreProperties(
    value = {"createdDate", "updatedDate", "isDeleted"},
    allowGetters = true)
public class PurchaseDetailVO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(hidden = true)
  private Long id;

  private Long purchaseId;
  private Long skuId;
  private Integer skuNum;
  private BigDecimal skuPrice;
  private Long wareId;
  private Integer status;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;
}
