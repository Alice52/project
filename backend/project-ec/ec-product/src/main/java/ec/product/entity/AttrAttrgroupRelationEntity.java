package ec.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 属性&属性分组关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:36:26
 */
@Data
@NoArgsConstructor
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;

  private Long attrId;

  private Long attrGroupId;

  private Integer attrSort;

  private LocalDateTime createdDate;

  private LocalDateTime updatedDate;

  @TableLogic private Integer isDeleted;

  public AttrAttrgroupRelationEntity(Long attrId, Long attrGroupId) {
    this.attrId = attrId;
    this.attrGroupId = attrGroupId;
  }

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
