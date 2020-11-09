package ec.product.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class AttrEntityVO {

  private Long enable;
  private Long attrGroupId;
  private String attrName;
  private Integer searchType;
  private String icon;
  private String valueSelect;
  private Integer attrType;
  private Integer valueType;
  private Long catelogId;
  private Integer showDesc;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @ApiModelProperty(hidden = true)
  private List<Long> catelogPath;

  @ApiModelProperty(hidden = true)
  private Long attrId;

  @ApiModelProperty(hidden = true)
  private String attrGroupName;

  @ApiModelProperty(hidden = true)
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
}
