package ec.ware.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Data
@TableName("wms_ware_order_task")
public class WareOrderTaskEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;
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
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  @TableLogic private Integer isDeleted;
}
