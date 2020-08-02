package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * The function of this class is to build a response.
 *
 * <p>Please do not add @ControllerAdvice annotation. <br>
 * due to it will catch earlier.
 *
 * <p>// TODO: I think it in strange.
 *
 * @author zack <br>
 * @create 2020-07-21 23:45 <br>
 * @project project-seckill <br>
 */
public class BaseExceptionHandler {

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
