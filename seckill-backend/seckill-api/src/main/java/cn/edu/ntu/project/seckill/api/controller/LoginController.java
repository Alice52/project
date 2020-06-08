package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.api.configuration.RedisUserKeyEnum;
import cn.edu.ntu.project.seckill.api.exception.JdCloudApiException;
import cn.edu.ntu.seckill.jdcloud.starter.service.LoginService;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static cn.edu.ntu.project.seckill.common.constant.Constants.ACCESS_TOKEN;

/**
 * @author zack <br>
 * @create 2020-05-31 11:34 <br>
 * @project seckill-backend <br>
 */
@Api
@RestController
@RequestMapping("/login")
public class LoginController {

  @Resource private RedisService redisService;
  @Resource private LoginService loginService;

  @GetMapping("/oauth-done")
  @ResponseBody
  public HashMap<String, String> OauthDone(
      @RequestParam(name = "code", required = true) String code, HttpServletResponse response) {

    ResponseEntity<JSONObject> jsonObject = loginService.jdClodOauthDone(code);
    JSONObject body = this.validateJdCloudResponseAndGet(jsonObject, code);

    String token = String.valueOf(body.get(ACCESS_TOKEN));
    this.addCookie(response, token);

    return MapUtil.of(ACCESS_TOKEN, token);
  }

  /**
   * Add access token to cookie.
   *
   * @param response
   * @param token
   */
  private void addCookie(HttpServletResponse response, String token) {
    ResponseEntity<JSONObject> jsonObject = loginService.jdClodGetUser(token);
    JSONObject body = this.validateJdCloudResponseAndGet(jsonObject, token);

    redisService.set(RedisUserKeyEnum.USER_KEY_NAME, token, String.valueOf(body.get("name")));
    Cookie cookie = new Cookie(ACCESS_TOKEN, token);
    cookie.setMaxAge(RedisUserKeyEnum.USER_KEY_NAME.expireSeconds());
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  private JSONObject validateJdCloudResponseAndGet(
      ResponseEntity<JSONObject> jsonObject, String... args) {
    if (jsonObject == null || !HttpStatus.OK.equals(jsonObject.getStatusCode())) {
      throw new JdCloudApiException(
          jsonObject == null ? null : jsonObject.getStatusCode(),
          jsonObject == null ? null : jsonObject.getBody(),
          args);
    }

    JSONObject body = jsonObject.getBody();

    return body;
  }
}
