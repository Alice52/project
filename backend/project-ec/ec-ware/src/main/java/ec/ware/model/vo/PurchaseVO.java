package ec.ware.model.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 采购信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Data
@JsonIgnoreProperties(
    value = {"createdDate", "updatedDate", "isDeleted"},
    allowGetters = true)
public class PurchaseVO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(hidden = true)
  private Long id;

  private Long assigneeId;
  private String assigneeName;
  private String phone;
  private Integer priority;
  private Integer status;
  private Long wareId;
  private BigDecimal amount;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;
}
