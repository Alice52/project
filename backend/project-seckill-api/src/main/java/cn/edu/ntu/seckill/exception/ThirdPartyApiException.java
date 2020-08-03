package cn.edu.ntu.seckill.exception;

import cn.hutool.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * @author zack <br>
 * @create 2020-07-19 16:03 <br>
 * @project backend <br>
 */
public class ThirdPartyApiException extends RuntimeException {
  private JSONObject rawBody;
  private HttpStatus rawStatus;
  private String[] args;

  public ThirdPartyApiException() {
  }

  public ThirdPartyApiException(HttpStatus httpStatus, JSONObject originBody, String... args) {
    this.rawStatus = httpStatus;
    this.rawBody = originBody;
    this.args = args;
  }

  public JSONObject getRawBody() {
    return rawBody;
  }

  public HttpStatus getRawStatus() {
    return rawStatus;
  }

  public String[] getArgs() {
    return args;
  }
}
