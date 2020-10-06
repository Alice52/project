package ec.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:06:03
 */
@Data
@TableName("sms_coupon")
public class CouponEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;
  private Integer couponType;
  private String couponImg;
  private String couponName;
  private Integer num;
  private BigDecimal amount;
  private Integer perLimit;
  private BigDecimal minPoint;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Integer useType;
  private String note;
  private Integer publishCount;
  private Integer useCount;
  private Integer receiveCount;
  private LocalDateTime enableStartTime;
  private LocalDateTime enableEndTime;
  private String code;
  private Integer memberLevel;
  private Integer publish;
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
