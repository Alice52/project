package cn.edu.ntu.seckill.annotation.constraint.validator;

import cn.edu.ntu.seckill.annotation.constraint.Mobile;
import cn.edu.ntu.seckill.utils.ValidatorUtil;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zack <br>
 * @create 2020-08-01 15:09 <br>
 * @project project-seckill <br>
 */
public class MobileDescriptor implements ConstraintValidator<Mobile, String> {

  private boolean required = false;

  @Override
  public void initialize(Mobile constraint) {
    this.required = constraint.required();
  }

  @Override
  public boolean isValid(String obj, ConstraintValidatorContext context) {

    if (!required && StrUtil.isBlank(obj)) {
      return true;
    }

    return ValidatorUtil.validateMobile(obj);
  }
}
