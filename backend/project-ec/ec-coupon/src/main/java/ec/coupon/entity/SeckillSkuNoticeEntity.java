package ec.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 秒杀商品通知订阅
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:06:03
 */
@Data
@TableName("sms_seckill_sku_notice")
public class SeckillSkuNoticeEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;
  private Long memberId;
  private Long skuId;
  private Long sessionId;
  private LocalDateTime subcribeTime;
  private LocalDateTime sendTime;
  private Integer noticeType;
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
