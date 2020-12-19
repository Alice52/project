package ec.ware.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 仓库信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Data
@JsonIgnoreProperties(
    value = {"createdDate", "updatedDate", "isDeleted"},
    allowGetters = true)
public class WareInfoVO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(hidden = true)
  private Long id;

  private String name;
  private String address;
  private String areacode;

  @ApiModelProperty(hidden = true)
  private LocalDateTime createdDate;

  @ApiModelProperty(hidden = true)
  private LocalDateTime updatedDate;

  @ApiModelProperty(hidden = true)
  private Integer isDeleted;
}
