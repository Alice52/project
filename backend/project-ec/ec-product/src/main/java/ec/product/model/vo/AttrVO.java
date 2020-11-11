package ec.product.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ec.common.annotation.AddGroup;
import ec.common.annotation.SpecifiedValue;
import ec.product.provider.AttrVOProvider;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-11-08 19:37 <br>
 * @project project-ec <br>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@GroupSequenceProvider(AttrVOProvider.class)
public class AttrVO {

  @SpecifiedValue(expectedLongs = {0, 1})
  private Long enable;

  @NotNull(groups = {BaseAttrType.class})
  private Long attrGroupId;

  private String attrName;

  @SpecifiedValue(expectedInts = {0, 1})
  private Integer searchType;

  private String icon;
  @NotNull private String valueSelect;

  @SpecifiedValue(expectedInts = {0, 1})
  private Integer attrType;

  @SpecifiedValue(expectedInts = {0, 1})
  private Integer valueType;

  @NotNull private Long catelogId;

  @SpecifiedValue(expectedInts = {0, 1})
  private Integer showDesc;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @ApiModelProperty(hidden = true)
  private List<Long> catelogPath;

  @ApiModelProperty(hidden = true)
  @Null(groups = AddGroup.class)
  private Long attrId;

  @ApiModelProperty(hidden = true)
  private String attrGroupName;

  @ApiModelProperty(hidden = true)
  private String catelogName;

  @ApiModelProperty(hidden = true)
  @Null
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  @Null
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  @Null
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

  @JsonProperty
  public Long getAttrId() {
    return attrId;
  }

  @JsonIgnore
  public void setAttrId(Long attrId) {
    this.attrId = attrId;
  }

  @JsonProperty
  public List<Long> getCatelogPath() {
    return catelogPath;
  }

  @JsonIgnore
  public void setCatelogPath(List<Long> catelogPath) {
    this.catelogPath = catelogPath;
  }

  public interface SaleAttrType {};

  public interface BaseAttrType {};
}
