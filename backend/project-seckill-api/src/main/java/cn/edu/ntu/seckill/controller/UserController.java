package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.UserApi;
import cn.edu.ntu.seckill.email.IMailSenderService;
import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.service.IUserService;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-07-26 19:00 <br>
 * @project project-seckill <br>
 */
@UserApi
@RestController
@Validated
@RequestMapping(
    value = "/user",
    //    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Slf4j
public class UserController extends BaseController {

  @Resource private IUserService userService;
  @Resource private IMailSenderService mailSenderService;

  /**
   * Register a new user.
   *
   * @param userVO
   * @return
   * @throws UnsupportedEncodingException
   */
  @PostMapping(value = "/register")
  public JSON register(@Valid @RequestBody UserVO userVO) throws UnsupportedEncodingException {

    userVO.setRegisterDate(LocalDateTime.now());

    String uuid = userService.register(userVO);
    return buildJson("id", uuid);
  }

  /**
   * @param email
   * @return
   * @throws UnsupportedEncodingException
   */
  @GetMapping(value = "/opt")
  public JSON generateValidationCode(
      @RequestParam(value = "email", required = true) @Email @NotNull String email) {

    String randomNumbers = RandomUtil.randomNumbers(6);
    boolean success =
        mailSenderService.sendSimpleMailMessage(email, "Seckill Validation Code", randomNumbers);
    log.info(randomNumbers);

    if (!success) {
      throw new BusinessException().new SendEmailException(email);
    }

    return buildJson("success", true);
  }
}
