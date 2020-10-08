package ec.common.annotation.discriptor;

import ec.common.annotation.ValidList;
import ec.common.component.ValidatorContext;
import ec.common.exception.ListValidException;

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
 * @create 2020-10-11 13:14 <br>
 * @project project-ec <br>
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

    Validator validator = ValidatorContext.getValidator();

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
