package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.UserApi;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.service.IUserService;
import cn.hutool.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-07-26 19:00 <br>
 * @project project-seckill <br>
 */
@Slf4j
@UserApi
@RestController
@Validated
@RequestMapping(
    value = "/user",
    //    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController extends BaseController {

  @Resource private IUserService userService;

  @PostMapping(value = "/register")
  public JSON register(@Valid @RequestBody UserVO userVO) throws UnsupportedEncodingException {

    userVO.setRegisterDate(LocalDateTime.now());

    String uuid = userService.register(userVO);
    return buildJson("id", uuid);
  }
}
