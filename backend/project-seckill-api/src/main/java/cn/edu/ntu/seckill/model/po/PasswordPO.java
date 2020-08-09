package cn.edu.ntu.seckill.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-09 14:45 <br>
 * @project project-seckill <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordPO extends BasePO {

  @NotNull private String userId;
  @NotNull private String password;
  @NotNull private String salt;
}
