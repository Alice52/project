package ec.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zack <br/>
 * @create 2020-10-11 13:13 <br/>
 * @project project-ec <br/>
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER})
public @interface UpdateGroup {


}
