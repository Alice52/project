package ec.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 订单
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Data
@TableName("oms_order")
public class OrderEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;
  private Long memberId;
  private String orderSn;
  private Long couponId;
  private LocalDateTime createTime;
  private String memberUsername;
  private BigDecimal totalAmount;
  private BigDecimal payAmount;
  private BigDecimal freightAmount;
  private BigDecimal promotionAmount;
  private BigDecimal integrationAmount;
  private BigDecimal couponAmount;
  private BigDecimal discountAmount;
  private Integer payType;
  private Integer sourceType;
  private Integer status;
  private String deliveryCompany;
  private String deliverySn;
  private Integer autoConfirmDay;
  private Integer integration;
  private Integer growth;
  private Integer billType;
  private String billHeader;
  private String billContent;
  private String billReceiverPhone;
  private String billReceiverEmail;
  private String receiverName;
  private String receiverPhone;
  private String receiverPostCode;
  private String receiverProvince;
  private String receiverCity;
  private String receiverRegion;
  private String receiverDetailAddress;
  private String note;
  private Integer confirmStatus;
  private Integer deleteStatus;
  private Integer useIntegration;
  private LocalDateTime paymentTime;
  private LocalDateTime deliveryTime;
  private LocalDateTime receiveTime;
  private LocalDateTime commentTime;
  private LocalDateTime modifyTime;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  @TableLogic private Integer isDeleted;

  @JsonProperty
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  @JsonIgnore
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  @JsonProperty
  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  @JsonIgnore
  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  @JsonProperty
  public Integer getIsDeleted() {
    return isDeleted;
  }

  @JsonIgnore
  public void setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
  }
}
