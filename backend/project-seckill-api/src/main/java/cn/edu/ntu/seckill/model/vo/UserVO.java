package cn.edu.ntu.seckill.model.vo;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-07-21 23:57 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

  @Ignore private String id;
  @Ignore private Long phone;

  @Max(100)
  @Min(0)
  private Integer age;

  @Ignore @DateTimeFormat private LocalDateTime registerDate;

  @NotBlank private String name;
  @Ignore @NotBlank private String password;

  @Email private String email;
}
