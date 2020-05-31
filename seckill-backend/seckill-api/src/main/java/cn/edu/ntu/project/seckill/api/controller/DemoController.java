package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.api.annotation.AccessLimit;
import cn.edu.ntu.project.seckill.api.configuration.RedisUserKeyEnum;
import cn.edu.ntu.project.seckill.api.vo.UserVo;
import cn.edu.ntu.project.seckill.common.model.ErrorResponse;
import cn.edu.ntu.seckill.jdcloud.starter.annotation.ValidateToken;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static cn.edu.ntu.project.seckill.common.constant.Constants.ACCESS_TOKEN;

/**
 * @author zack <br>
 * @create 2020-05-17 12:37 <br>
 * @project seckill-backend <br>
 */
@Api
@RestController
@RequestMapping("/demo")
@ApiResponses({
  @ApiResponse(code = 400, message = "Internal Error", response = ErrorResponse.class)
})
public class DemoController {

  @Resource RedisService redisService;

  @GetMapping("/validate")
  public String validate(@Valid UserVo userVo) {

    return "success ";
  }

  @AccessLimit
  @GetMapping("/success")
  public String success() {

    return "success ";
  }

  @ValidateToken
  @GetMapping("/error")
  public ErrorResponse error() {

    throw new RuntimeException("invalid token");
  }

  @GetMapping("/redis")
  public Object redis(HttpServletRequest request) {

    String name =
        redisService.get(
            RedisUserKeyEnum.USER_KEY_NAME, getCookieValue(request, ACCESS_TOKEN), String.class);

    return name;
  }

  private String getCookieValue(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length <= 0) {
      return null;
    }
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(cookieName)) {
        return cookie.getValue();
      }
    }
    return null;
  }
}
