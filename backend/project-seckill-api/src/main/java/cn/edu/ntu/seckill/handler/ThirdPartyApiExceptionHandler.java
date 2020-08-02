package cn.edu.ntu.seckill.handler;

import cn.edu.ntu.model.ErrorMessageEnum;
import cn.edu.ntu.model.ErrorResponse;
import cn.edu.ntu.seckill.exception.ThirdPartyApiException;
import cn.hutool.core.map.MapUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author zack <br>
 * @create 2020-08-02 17:01 <br>
 * @project project-seckill <br>
 */
@ControllerAdvice
@ResponseBody
public class ThirdPartyApiExceptionHandler extends BaseExceptionHandler {

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
}
