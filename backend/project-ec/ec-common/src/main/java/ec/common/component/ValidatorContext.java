package ec.common.component;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Validator;

/**
 * @author zack <br>
 * @create 2020-10-11 13:15 <br>
 * @project project-ec <br>
 */
@Component
public class ValidatorContext {
  private static Validator validator;

  public static Validator getValidator() {
    return validator;
  }

  @Resource
  public void setValidator(Validator validator) {
    ValidatorContext.validator = validator;
  }
}
