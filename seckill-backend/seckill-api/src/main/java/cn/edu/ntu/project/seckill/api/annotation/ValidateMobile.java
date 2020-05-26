package cn.edu.ntu.project.seckill.api.annotation;

import cn.edu.ntu.project.seckill.api.annotation.descriptor.ValidateMobileDescriptor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zack <br/>
 * @create 2020-05-26 21:49 <br/>
 * @project seckill-backend <br/>
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidateMobileDescriptor.class })
public @interface ValidateMobile {

    boolean required() default true;

    String message() default "Wrong format of phone number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
