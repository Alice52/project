package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zack <br>
 * @create 2020-08-08 17:31 <br>
 * @project project-seckill <br>
 */
@ControllerAdvice
@ResponseBody
@Order(100)
public class DatabaseExceptionHandler {

  @ExceptionHandler(MySQLSyntaxErrorException.class)
  public ResponseEntity handleMySQLSyntaxErrorException(MySQLSyntaxErrorException ex) {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.SQL_SYNTAX_ERROR);

    return DefaultExceptionHandler.buildResponseEntity(
        errorResponse, HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }
}
