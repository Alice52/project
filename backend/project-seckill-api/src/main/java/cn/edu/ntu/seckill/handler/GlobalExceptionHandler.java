package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br/>
 * @create 2020-08-02 17:51 <br/>
 * @project project-seckill <br/>
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.SYSTEM_ERROR);

    return buildResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR, e);
  }
}
