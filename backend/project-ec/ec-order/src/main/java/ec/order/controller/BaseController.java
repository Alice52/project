package ec.order.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import ec.common.error.ErrorResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author zack <br>
 * @create 2020-10-06 11:31 <br>
 * @project project-ec <br>
 */
@ApiResponses({
  @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
  @ApiResponse(code = 500, message = "Internal Error", response = ErrorResponse.class)
})
@CrossOrigin(
    origins = {"*"},
    allowCredentials = "true")
public class BaseController {

  /**
   * This is to build json response for single value, such as id, count, which is basic type.
   *
   * @param key
   * @param value
   * @return
   */
  static JSON buildJson(String key, Object value) {
    JSONObject object = new JSONObject();
    object.put(key, value);

    return object;
  }
}
