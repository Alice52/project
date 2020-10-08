package ec.thirdparty.handler;

import ec.common.error.handler.BaseValidationExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * There are 4 types exceptions.
 *
 * @author zack <br>
 * @create 2020-10-11 11:51 <br>
 * @project project-ec <br>
 */
@Order(100)
@Slf4j
@ResponseBody
@RestControllerAdvice
public class BeanValidationExceptionHandler extends BaseValidationExceptionHandler {}
