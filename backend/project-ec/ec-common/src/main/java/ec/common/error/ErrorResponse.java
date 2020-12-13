package ec.common.error;

import lombok.Data;

import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-10-06 11:33 <br>
 * @project project-ec <br>
 */
@Data
public class ErrorResponse {

  private Integer errorCode;
  private String errorMsg;
  private Map<String, Object> parameters;

  public static ErrorResponse error(ErrorMessageEnum errorMessageEnum) {
    return new ErrorResponse(errorMessageEnum);
  }

  private ErrorResponse(ErrorMessageEnum errorMessageEnum) {
    this.errorMsg = errorMessageEnum.getErrorMsg();
    this.errorCode = errorMessageEnum.getErrorCode();
  }
}
