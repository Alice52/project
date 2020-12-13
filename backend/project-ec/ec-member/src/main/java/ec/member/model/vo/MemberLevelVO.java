package ec.member.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ec.common.annotation.AddGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020/12/12 <br>
 * @project project-ec <br>
 */
@Data
public class MemberLevelVO {
  @ApiModelProperty(hidden = true)
  @Null(groups = AddGroup.class)
  private Long id;

  @NotBlank private String name;
  @NotNull private Integer growthPoint;
  @NotNull private Integer defaultStatus;
  @NotNull private BigDecimal freeFreightPoint;
  @NotNull private Integer commentGrowthPoint;
  @NotNull private Integer priviledgeFreeFreight;
  @NotNull private Integer priviledgeMemberPrice;
  @NotNull private Integer priviledgeBirthday;
  @NotNull private String note;

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
}
