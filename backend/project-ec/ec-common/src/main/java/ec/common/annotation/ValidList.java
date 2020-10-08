package ec.common.annotation;

import ec.common.annotation.discriptor.ValidListDescriptor;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zack <br>
 * @create 2020-10-11 13:13 <br>
 * @project project-ec <br>
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER})
@Constraint(validatedBy = {ValidListDescriptor.class})
public @interface ValidList {

  /** specify group. */
  Class<?>[] values() default {Default.class};

  boolean quickFail() default false;

  /** This is also no use, due to we need inner message. */
  String message() default "";

  /** donot use it due to it cannot apply to each list value. */
  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
