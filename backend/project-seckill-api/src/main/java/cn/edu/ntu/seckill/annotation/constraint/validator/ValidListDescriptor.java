package cn.edu.ntu.seckill.annotation.constraint.validator;

import cn.edu.ntu.seckill.annotation.constraint.ValidList;
import cn.edu.ntu.seckill.component.ValidatorUtils;
import cn.edu.ntu.seckill.exception.ListValidException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zack <br>
 * @create 2020-08-02 10:23 <br>
 * @project validation <br>
 */
public class ValidListDescriptor implements ConstraintValidator<ValidList, List> {
  Class<?>[] groups = null;
  boolean quickFail = false;

  @Override
  public void initialize(ValidList constraintAnnotation) {
    groups = constraintAnnotation.values();
    quickFail = constraintAnnotation.quickFail();
  }

  @Override
  public boolean isValid(List values, ConstraintValidatorContext context) {

    Validator validator = ValidatorUtils.getValidator();

    Map<Integer, Set<ConstraintViolation<Object>>> errors = new HashMap<>();
    int size = values.size();
    for (int i = 0; i < size; i++) {
      Set<ConstraintViolation<Object>> violations = validator.validate(values.get(i), groups);
      if (violations.size() > 0) {
        errors.put(i, violations);
        if (quickFail) {
          throw new ListValidException(errors);
        }
      }
    }

    if (errors.size() > 0) {
      throw new ListValidException(errors);
    }

    return true;
  }
}
