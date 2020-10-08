package ec.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ec.common.annotation.AddGroup;
import ec.common.annotation.SpecifiedValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-10-08 17:14 <br>
 * @project project-ec <br>
 */
@Data
@ToString
@EqualsAndHashCode
public class CategoryEntityVO {

  @ApiModelProperty(hidden = true)
  @Null(groups = AddGroup.class)
  private Long catId;

  @NotBlank(groups = {AddGroup.class})
  private String name;

  @NotBlank(groups = {AddGroup.class})
  private Long parentCid;

  @NotBlank(groups = {AddGroup.class})
  private Integer catLevel;

  @SpecifiedValue(expectedInts = {0, 1})
  @NotBlank(groups = {AddGroup.class})
  private Integer showStatus;

  @PositiveOrZero private Integer sort;

  @NotBlank(groups = AddGroup.class)
  @URL
  private String icon;

  @NotBlank(groups = AddGroup.class)
  private String productUnit;

  private Integer productCount;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @ApiModelProperty(hidden = true)
  private List<CategoryEntityVO> children;

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
