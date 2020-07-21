package cn.edu.ntu.projectname.controller;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import cn.edu.ntu.projectname.exception.ThirdPartyApiException;
import cn.hutool.core.map.MapUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author zack <br>
 * @create 2020-07-21 23:45 <br>
 * @project project-name <br>
 */
public class BaseExceptionHandler {

  @ExceptionHandler(ThirdPartyApiException.class)
  public ResponseEntity handleThirdPartyApiException(
      ThirdPartyApiException e, HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.THIRD_PARTY_CALL_EXCEPTION);

    HashMap<String, Object> map = MapUtil.of("rawStatus", e.getRawStatus());
    map.put("rowBody", e.getRawBody());
    map.put("rowArgs", e.getArgs());
    errorResponse.setParameters(map);

    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
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

    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.UNKNOWN_EXCEPTION);

    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity handleNoHandlerFoundException(
      NoHandlerFoundException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.NOT_FOUND);

    HashMap<String, Object> map = new HashMap<>(4);
    map.put("path", request.getRequestURI());
    errorResponse.setParameters(map);

    return buildResponseEntity(errorResponse, HttpStatus.NOT_FOUND, e);
  }

  protected static ResponseEntity buildResponseEntity(
      @NotBlank ErrorResponse errorResponse, HttpStatus status, Exception ex) {

    Map<String, Object> parameters =
        Optional.ofNullable(errorResponse.getParameters()).orElse(new HashMap<>(4));
    parameters.put("cause", ex.getCause());
    parameters.put("message", ex.getMessage());

    errorResponse.setParameters(parameters);
    return new ResponseEntity<>(errorResponse, status);
  }
}
