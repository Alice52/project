package cn.edu.ntu.seckill.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020-08-09 16:57 <br>
 * @project project-seckill <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO extends BaseBO {

  @NotBlank private String token;
  private String phone;
  private String name;
  private Integer age;
  private String email;
}
