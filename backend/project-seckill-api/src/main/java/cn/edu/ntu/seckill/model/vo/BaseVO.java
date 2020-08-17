package cn.edu.ntu.seckill.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2020-08-17 21:33 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO {

  @ApiModelProperty(hidden = true)
  private String id;
}
