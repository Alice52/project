package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.common.model.ErrorMessageEnum;
import cn.edu.ntu.project.seckill.common.model.ErrorResponse;
import cn.hutool.core.map.MapUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity handleRuntimeException(RuntimeException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.UNKNOWN_EXCEPTION);
    HashMap<String, Object> map = MapUtil.of("cause", e.getCause());
    map.put("message", e.getMessage());
    errorResponse.setParameters(map);

    return new ResponseEntity<>(errorResponse, status);
  }
}
