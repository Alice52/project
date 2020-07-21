package cn.edu.ntu.projectname.controller;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import cn.edu.ntu.projectname.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020-07-21 23:48 <br>
 * @project project-name <br>
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {

  @ExceptionHandler(UserException.UserLoginException.class)
  public ResponseEntity handleUserLoginException(
      UserException.UserLoginException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.INVALID_TOKEN);

    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }
}
