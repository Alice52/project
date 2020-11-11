package ec.common.annotation;

import ec.common.annotation.discriptor.SpecifiedDoublesDescriptor;
import ec.common.annotation.discriptor.SpecifiedIntsDescriptor;
import ec.common.annotation.discriptor.SpecifiedLongsDescriptor;
import ec.common.annotation.discriptor.SpecifiedStrsDescriptor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zack <br>
 * @create 2020-10-11 14:19 <br>
 * @project project-ec <br>
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER})
@Constraint(
    validatedBy = {
      SpecifiedIntsDescriptor.class,
      SpecifiedStrsDescriptor.class,
      SpecifiedLongsDescriptor.class,
      SpecifiedDoublesDescriptor.class
    })
public @interface SpecifiedValue {
  String message() default "参数值不在预期范围内";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int[] expectedInts() default {};

  long[] expectedLongs() default {};

  double[] expectedDoubles() default {};

  String[] expectedStrs() default {};
}
