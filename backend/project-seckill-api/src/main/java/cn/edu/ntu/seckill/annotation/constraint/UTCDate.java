package cn.edu.ntu.seckill.annotation.constraint;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zack <br>
 * @create 2020-08-01 15:24 <br>
 * @project project-seckill <br>
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD})
public @interface UTCDate {
  // TODO: need make sure system date is UTC.
}
