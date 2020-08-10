package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import cn.edu.ntu.seckill.exception.BusinessException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zack <br>
 * @create 2020-07-21 23:48 <br>
 * @project project-seckill <br>
 */
@Order(100)
@ResponseBody
@ControllerAdvice
public class BusinessExceptionHandler {

  @ExceptionHandler(BusinessException.SendEmailException.class)
  public ResponseEntity handleSendEmailException(BusinessException.SendEmailException e) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.EMAIL_SEND_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }
}
