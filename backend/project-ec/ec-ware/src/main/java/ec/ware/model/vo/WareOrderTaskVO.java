package ec.ware.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Data
@JsonIgnoreProperties(
    value = {"createdDate", "updatedDate", "isDeleted"},
    allowGetters = true)
public class WareOrderTaskVO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(hidden = true)
  private Long id;

  private Long orderId;
  private String orderSn;
  private String consignee;
  private String consigneeTel;
  private String deliveryAddress;
  private String orderComment;
  private Integer paymentWay;
  private Integer taskStatus;
  private String orderBody;
  private String trackingNo;
  private LocalDateTime createTime;
  private Long wareId;
  private String taskComment;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;
}
