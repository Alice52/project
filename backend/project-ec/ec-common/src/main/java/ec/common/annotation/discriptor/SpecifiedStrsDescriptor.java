package ec.common.annotation.discriptor;

import ec.common.annotation.SpecifiedValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zack <br>
 * @create 2020-10-11 14:22 <br>
 * @project project-ec <br>
 */
public class SpecifiedStrsDescriptor implements ConstraintValidator<SpecifiedValue, String> {

  Set<String> values = new HashSet<>();

  @Override
  public void initialize(SpecifiedValue constraintAnnotation) {
    Arrays.stream(constraintAnnotation.expectedStrs()).forEach(x -> values.add(x));
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return values.contains(value);
  }
}
