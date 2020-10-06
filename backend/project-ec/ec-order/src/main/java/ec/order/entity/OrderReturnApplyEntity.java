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
 * 订单退货申请
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Data
@TableName("oms_order_return_apply")
public class OrderReturnApplyEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;
  private Long orderId;
  private Long skuId;
  private String orderSn;
  private LocalDateTime createTime;
  private String memberUsername;
  private BigDecimal returnAmount;
  private String returnName;
  private String returnPhone;
  private Integer status;
  private LocalDateTime handleTime;
  private String skuImg;
  private String skuName;
  private String skuBrand;
  private String skuAttrsVals;
  private Integer skuCount;
  private BigDecimal skuPrice;
  private BigDecimal skuRealPrice;
  private String reason;
  private String description述;
  private String descPics;
  private String handleNote;
  private String handleMan;
  private String receiveMan;
  private LocalDateTime receiveTime;
  private String receiveNote;
  private String receivePhone;
  private String companyAddress;
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
