package cn.edu.ntu.seckill.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-10 22:40 <br>
 * @project project-seckill <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordBO {
  @NotNull private String userId;
  @NotNull private String password;
  @NotNull private String salt;
}
