package cn.edu.ntu.seckill.model.vo;

import cn.edu.ntu.seckill.annotation.constraint.Mobile;
import cn.edu.ntu.seckill.annotation.constraint.UTCDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

  @ApiModelProperty(hidden = true)
  private String id;

  @Mobile private String phone;

  @Max(100)
  @Min(0)
  @ApiModelProperty(required = true)
  private Integer age;

  @UTCDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime registerDate;

  @NotBlank private String name;

  /**
   * @JsonIgnore can be received args, but will not be serialized in vo.
   *
   * <p>And @JsonIgnoreProperties not worked.
   */
  @NotBlank private String password;

  @Email private String email;
  private String validationCode;

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }

  @JsonIgnore
  public String getValidationCode() {
    return validationCode;
  }

  @JsonProperty
  public void setValidationCode(String validationCode) {
    this.validationCode = validationCode;
  }
}
