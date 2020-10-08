package ec.product.handler;

import ec.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020-10-10 12:44 <br>
 * @project project-ec <br>
 */
@Order
@ControllerAdvice
@ResponseBody
@Slf4j
public class DefaultExceptionHandler {

  @ExceptionHandler({Exception.class})
  public R handleException(Exception e, HttpServletRequest request) {

    return R.error(500, e.getMessage()).put("detail", e.getStackTrace());
  }
}
