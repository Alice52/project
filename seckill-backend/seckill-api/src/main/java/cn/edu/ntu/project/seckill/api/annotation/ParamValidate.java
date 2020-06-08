//package cn.edu.ntu.project.seckill.api.annotation;
//
///**
// * @author zack <br>
// * @create 2020-06-08 22:24 <br>
// * @project seckill-backend <br>
// */
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//public @interface ParamValidate {
//  String name();
//
//  String regex() default "";
//
//  boolean notNull() default false;
//
//  String message() default "";
//
//  int maxLen();
//
//  int minLen() default -1;
//
//  int maxVal() default -1;
//
//  int minVal() default -1;
//
//  String dateFormat() default "";
//}
