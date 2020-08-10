package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import cn.edu.ntu.seckill.exception.UserException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020-08-10 22:15 <br>
 * @project project-seckill <br>
 */
@Order(100)
@ResponseBody
@ControllerAdvice
public class UserExceptionHandler {
  @ExceptionHandler(UserException.UserLoginException.class)
  public ResponseEntity handleUserLoginException(
      UserException.UserLoginException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.INVALID_TOKEN_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(UserException.UserNotExistenceException.class)
  public ResponseEntity handleUserUserNotExistenceException(
      UserException.UserNotExistenceException e) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.User_NOT_EXIST_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(UserException.UserAlreadyExistenceException.class)
  public ResponseEntity handleUserAlreadyExistenceException(
      UserException.UserAlreadyExistenceException e) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.USER_DUPLICATED_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(UserException.InvalidValidationCodeException.class)
  public ResponseEntity handleInvalidValidationCodeException(
      UserException.InvalidValidationCodeException e) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.VALIDATION_CODE_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(UserException.InvalidTokenException.class)
  public ResponseEntity handleInvalidTokenException(UserException.InvalidTokenException e) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.INVALID_TOKEN_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED, e);
  }

  @ExceptionHandler(UserException.InvalidPassword.class)
  public ResponseEntity handleInvalidPassword(UserException.InvalidPassword e) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.USERNAME_OR_PASSWORD_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }
}
