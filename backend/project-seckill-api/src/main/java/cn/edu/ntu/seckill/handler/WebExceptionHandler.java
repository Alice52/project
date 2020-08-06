package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author zack <br>
 * @create 2020-08-02 17:07 <br>
 * @project project-seckill <br>
 */
@Order(100)
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity handleNoHandlerFoundException(
      NoHandlerFoundException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.NOT_FOUND);

    HashMap<String, Object> map = new HashMap<>(4);
    map.put("path", request.getRequestURI());
    errorResponse.setParameters(map);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.NOT_FOUND, e);
  }
}
