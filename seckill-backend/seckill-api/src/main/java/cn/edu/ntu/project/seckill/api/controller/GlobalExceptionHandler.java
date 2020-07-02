package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.api.exception.AccessLimitException;
import cn.edu.ntu.project.seckill.api.exception.JdCloudApiException;
import cn.edu.ntu.project.seckill.api.exception.SecKillException;
import cn.edu.ntu.project.seckill.api.exception.UserException;
import cn.edu.ntu.project.seckill.common.model.ErrorMessageEnum;
import cn.edu.ntu.project.seckill.common.model.ErrorResponse;
import cn.hutool.core.map.MapUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author zack <br>
 * @create 2020-05-26 23:20 <br>
 * @project seckill-backend <br>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(JdCloudApiException.class)
  public ResponseEntity handleJdCloudApiException(
      JdCloudApiException e, HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.JDCLOUD_TOKEN_EXCEPTION);

    HashMap<String, Object> map = MapUtil.of("rawStatus", e.getRawStatus());
    map.put("rowBody", e.getRawBody());
    map.put("rowArgs", e.getArgs());
    errorResponse.setParameters(map);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity handleBindException(BindException e, HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.BIND_EXCEPTION);
    FieldError field = e.getBindingResult().getFieldError();
    HashMap<String, Object> map = MapUtil.of("field", field.getField());
    map.put("rejectedValue", field.getRejectedValue());
    map.put("objectName", field.getObjectName());
    map.put("message", field.getDefaultMessage());
    errorResponse.setParameters(map);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.UNKNOWN_EXCEPTION);
    HashMap<String, Object> map = MapUtil.of("cause", e.getCause());
    map.put("message", e.getMessage());
    errorResponse.setParameters(map);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserException.UserAlreadyExistenceException.class)
  public ResponseEntity handleUserAlreadyExistenceException(
      UserException.UserAlreadyExistenceException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.INVALID_NICKNAME);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserException.UserLoginException.class)
  public ResponseEntity handleUserLoginException(
      UserException.UserLoginException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.INVALID_TOKEN);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SecKillException.RepeatedSecKillException.class)
  public ResponseEntity handleRepeatedSecKillException(
      SecKillException.RepeatedSecKillException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.REPEATED_SECKILL_EXCEPTION);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SecKillException.SecKillGoodsOverException.class)
  public ResponseEntity handleSecKillGoodsOverException(
      SecKillException.SecKillGoodsOverException e, HttpServletRequest request) {
    ErrorResponse errorResponse =
        ErrorResponse.error(ErrorMessageEnum.SECKILL_GOODS_OVER_EXCEPTION);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessLimitException.class)
  public ResponseEntity handleAccessLimitException(
      AccessLimitException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.ACCESS_LIMIT);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
