package cn.edu.ntu.seckill.component;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Validator;

/**
 * @author zack <br>
 * @create 2020-08-02 10:34 <br>
 * @project validation <br>
 */
@Component
public final class ValidatorUtils {
  private static Validator validator;

  public static Validator getValidator() {
    return validator;
  }

  @Resource
  public void setValidator(Validator validator) {
    ValidatorUtils.validator = validator;
  }
}
