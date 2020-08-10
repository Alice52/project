package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.model.ErrorResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author zack <br>
 * @create 2020-07-26 19:51 <br>
 * @project project-seckill <br>
 */
@ApiResponses({
  @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
  @ApiResponse(code = 500, message = "Internal Error", response = ErrorResponse.class)
})
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
