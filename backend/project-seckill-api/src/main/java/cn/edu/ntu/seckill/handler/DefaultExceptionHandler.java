package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

/**
 * The function of this class is to build a response.
 *
 * <p>Please do not add @ControllerAdvice annotation. <br>
 * due to it will catch earlier.
 *
 * <p> https://github.com/Alice52/project/issues/33
 *
 * @author zack <br>
 * @create 2020-07-21 23:45 <br>
 * @project project-seckill <br>
 */
@Order(LOWEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler({Exception.class})
  public ResponseEntity handleException(Exception e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.SYSTEM_ERROR);

    return buildResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR, e);
  }

  protected static ResponseEntity buildResponseEntity(
      ErrorResponse errorResponse, HttpStatus status, Exception ex) {

    Map<String, Object> parameters = new HashMap<>(2);
    parameters.put("message", ex.getMessage());
    parameters.put("cause", ex.getCause());

    errorResponse.setParameters(parameters);
    return new ResponseEntity<>(errorResponse, status);
  }

  protected static ResponseEntity buildResponseEntity(
      ErrorResponse errorResponse, HttpStatus status) {
    return new ResponseEntity<>(errorResponse, status);
  }
}
