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
 * 订单项信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Data
@TableName("oms_order_item")
public class OrderItemEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;
  private Long orderId;
  private String orderSn;
  private Long spuId;
  private String spuName;
  private String spuPic;
  private String spuBrand;
  private Long categoryId;
  private Long skuId;
  private String skuName;
  private String skuPic;
  private BigDecimal skuPrice;
  private Integer skuQuantity;
  private String skuAttrsVals;
  private BigDecimal promotionAmount;
  private BigDecimal couponAmount;
  private BigDecimal integrationAmount;
  private BigDecimal realAmount;
  private Integer giftIntegration;
  private Integer giftGrowth;
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
