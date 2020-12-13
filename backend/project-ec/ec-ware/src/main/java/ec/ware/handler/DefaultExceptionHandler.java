package ec.ware.handler;

import ec.common.error.handler.BaseDefaultExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zack <br>
 * @create 2020-10-10 12:44 <br>
 * @project project-ec <br>
 */
@Order
@ControllerAdvice
@ResponseBody
@Slf4j
public class DefaultExceptionHandler extends BaseDefaultExceptionHandler {}
