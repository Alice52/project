package cn.edu.ntu.model;

import java.util.Map;

/**
 * if success, system will response data only; <br>
 * if failed, system will response JsonResult with code, message and parameters; <br>
 *
 * @author zack <br>
 * @create 2020-05-17 11:44 <br>
 * @project seckill-backend <br>
 */
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

  public Integer getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public Map<String, Object> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }
}
