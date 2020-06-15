package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.exception.UserException;
import cn.edu.ntu.project.seckill.api.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * password should be md5 in front again.
 *
 * @author zack <br>
 * @create 2020-06-08 19:38 <br>
 * @project seckill-backend <br>
 */
@Api
@RequestMapping("/user")
@RestController
public class UserController {

  @Resource private UserService userService;

  // @ParamsValidate(value = {@ParamValidate(name = "name", notNull = true, message = "不能为空", maxLen
  // = 200)})
  @PostMapping("/login")
  public boolean login(@Valid SeckillUser user) {

    boolean validLogin = userService.doLogin(user);

    if (validLogin) {
      return true;
    } else {
      throw new UserException().new UserLoginException();
    }
  }

  @PostMapping("/register")
  public int register(@Valid SeckillUser user) {

    return userService.validateAndSave(user);
  }
}
