package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * The function of this class is to build a response.
 *
 * <p>Please do not add @ControllerAdvice annotation. <br>
 * due to it will catch earlier.
 *
 * <p>https://github.com/Alice52/project/issues/33
 *
 * @author zack <br>
 * @create 2020-07-21 23:45 <br>
 * @project project-seckill <br>
 */
@Order
@ControllerAdvice
@ResponseBody
@Slf4j
public class DefaultExceptionHandler {

  @ExceptionHandler({Exception.class})
  public ResponseEntity handleException(Exception e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.SYSTEM_ERROR);

    return buildResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR, e);
  }

  protected static ResponseEntity buildResponseEntity(
      ErrorResponse errorResponse, HttpStatus status, Exception ex) {
    log.error("exception info: ", ex);

    Map<String, Object> message =
        Optional.ofNullable(errorResponse.getParameters())
            .orElse(MapUtil.of("message", ex.getMessage()));

    errorResponse.setParameters(message);
    return new ResponseEntity<>(errorResponse, status);
  }

  protected static ResponseEntity buildResponseEntity(
      ErrorResponse errorResponse, HttpStatus status) {
    return new ResponseEntity<>(errorResponse, status);
  }
}
