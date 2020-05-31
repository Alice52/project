package cn.edu.ntu.seckill.jdcloud.starter.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zack <br>
 * @create 2020-05-26 21:33 <br>
 * @project seckill-backend <br>
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ValidateToken {}
