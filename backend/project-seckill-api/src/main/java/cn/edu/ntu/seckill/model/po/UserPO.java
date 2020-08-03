package cn.edu.ntu.seckill.model.po;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
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
@Validated
public class UserPO {

  @Length(max = 36, min = 36)
  private String id;

  private Long phone;

  @NotBlank private String name;

  @Max(100)
  @Min(0)
  private Integer age;

  @Email private String email;
  @NotNull private String password;
  @NotNull private String salt;

  @DateTimeFormat private LocalDateTime registerDate;
}