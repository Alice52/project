package cn.edu.ntu.seckill.annotation.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zack <br>
 * @create 2020-07-26 19:49 <br>
 * @project project-seckill <br>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserApi {}
