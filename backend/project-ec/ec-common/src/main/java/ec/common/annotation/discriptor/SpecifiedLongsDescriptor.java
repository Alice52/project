package ec.common.annotation.discriptor;

import cn.hutool.core.util.ObjectUtil;
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
public class SpecifiedLongsDescriptor implements ConstraintValidator<SpecifiedValue, Long> {

  Set<Long> values = new HashSet<>();

  @Override
  public void initialize(SpecifiedValue constraintAnnotation) {
    Arrays.stream(constraintAnnotation.expectedLongs()).forEach(x -> values.add(x));
  }

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {

    if (ObjectUtil.isNull(value)) {
      return true;
    }

    return values.contains(value);
  }
}
