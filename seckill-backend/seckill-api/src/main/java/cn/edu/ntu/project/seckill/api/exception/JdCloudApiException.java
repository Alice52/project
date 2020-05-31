package cn.edu.ntu.project.seckill.api.exception;

import cn.hutool.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * @author zack <br>
 * @create 2020-05-31 12:37 <br>
 * @project seckill-backend <br>
 */
public class JdCloudApiException extends RuntimeException {
  private JSONObject rawBody;
  private HttpStatus rawStatus;
  private String[] args;

  public JdCloudApiException(HttpStatus httpStatus, JSONObject originBody, String... args) {
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
