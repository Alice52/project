package cn.edu.ntu.project.seckill.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zack <br>
 * @create 2020-05-26 22:20 <br>
 * @project seckill-backend <br>
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
  int seconds() default 10;

  int maxCount() default 5;

  boolean needLogin() default true;
}
