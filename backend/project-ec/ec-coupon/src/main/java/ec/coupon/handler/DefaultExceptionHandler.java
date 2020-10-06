package ec.coupon.handler;

import cn.hutool.core.map.MapUtil;
import ec.coupon.model.ErrorMessageEnum;
import ec.coupon.model.ErrorResponse;
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
 * @author zack <br>
 * @create 2020-10-06 12:04 <br>
 * @project project-ec <br>
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
