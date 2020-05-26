package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.api.annotation.AccessLimit;
import cn.edu.ntu.project.seckill.api.annotation.ValidateMobile;
import cn.edu.ntu.project.seckill.api.configuration.RedisUserKeyEnum;
import cn.edu.ntu.project.seckill.api.vo.UserVo;
import cn.edu.ntu.project.seckill.common.model.ErrorMessageEnum;
import cn.edu.ntu.project.seckill.common.model.ErrorResponse;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

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

  @GetMapping("/error")
  public ErrorResponse error() {

    throw new RuntimeException("invalid token");
  }

  @GetMapping("/redis")
  public Object redis() {

    boolean zack = redisService.set(RedisUserKeyEnum.USER_KEY_ID, "zack", 123);

    if (zack) {
      return redisService.get(RedisUserKeyEnum.USER_KEY_ID, "zack", Integer.class);
    }

    return null;
  }
}
