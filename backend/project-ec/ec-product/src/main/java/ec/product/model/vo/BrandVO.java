package ec.product.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ec.common.annotation.AddGroup;
import ec.common.annotation.SpecifiedValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * 品牌
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:36:26
 */
@Data
@JsonIgnoreProperties(
    value = {"createdDate", "updatedDate", "isDeleted"},
    allowGetters = true)
public class BrandVO {

  @ApiModelProperty(hidden = true)
  @Null(groups = AddGroup.class)
  private Long brandId;

  @NotBlank(groups = AddGroup.class)
  private String name;

  @NotNull(groups = AddGroup.class)
  @URL
  private String logo;

  private String descript;

  @SpecifiedValue(expectedInts = {0, 1})
  private Integer showStatus;

  @Length(min = 1, max = 1)
  @Pattern(regexp = "^[a-zA-Z]$")
  private String firstLetter;

  @PositiveOrZero private Integer sort;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;
}
