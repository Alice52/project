package cn.edu.ntu.project.seckill.api.annotation;

/**
 * @author zack <br>
 * @create 2020-05-26 22:20 <br>
 * @project seckill-backend <br>
 */
public @interface AccessLimit {
  int seconds() default 10;

  int maxCount() default 5;

  boolean needLogin() default true;
}
