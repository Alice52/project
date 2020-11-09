package ec.product.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ec.common.annotation.AddGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

/**
 * 品牌分类关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:36:26
 */
@Data
public class CategoryBrandRelationVO {

  @ApiModelProperty(hidden = true)
  @Null(groups = AddGroup.class)
  private Long id;

  @NotNull(groups = AddGroup.class)
  private Long brandId;

  @NotNull(groups = AddGroup.class)
  private Long catelogId;

  private String brandName;
  private String catelogName;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;

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
