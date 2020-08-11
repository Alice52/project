package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import cn.edu.ntu.seckill.exception.GoodsException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020-08-11 23:10 <br>
 * @project project-seckill <br>
 */
@Order(100)
@ResponseBody
@ControllerAdvice
public class GoodsExceptionHandler {

  @ExceptionHandler(GoodsException.GoodsNameDuplicateException.class)
  public ResponseEntity handleGoodsNameDuplicateException(
      GoodsException.GoodsNameDuplicateException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.GOODS_NAME_DUPLICATED_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(GoodsException.GoodsNotExistenceException.class)
  public ResponseEntity handleGoodsNotExistenceException(
      GoodsException.GoodsNotExistenceException e, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.GOODS_NOT_EXISTENCE_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, e);
  }
}
