package cn.edu.ntu.seckill.model.po;

import cn.edu.ntu.seckill.annotation.constraint.Mobile;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-07-26 19:12 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPO extends BasePO {

  @Mobile private String phone;

  @NotBlank private String name;

  @Max(100)
  @Min(0)
  private Integer age;

  @NotBlank @Email private String email;
  @DateTimeFormat private LocalDateTime registeredDate;
}
