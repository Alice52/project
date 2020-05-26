package cn.edu.ntu.project.seckill.api.annotation.descriptor;

import cn.edu.ntu.project.seckill.api.annotation.ValidateMobile;
import cn.edu.ntu.project.seckill.api.utils.ValidatorUtil;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zack <br/>
 * @create 2020-05-26 21:51 <br/>
 * @project seckill-backend <br/>
 */
public class ValidateMobileDescriptor implements ConstraintValidator<ValidateMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(ValidateMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required) {
            return ValidatorUtil.isMobile(value);
        }else {
            if(StrUtil.isEmpty(value)) {
                return true;
            }else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
