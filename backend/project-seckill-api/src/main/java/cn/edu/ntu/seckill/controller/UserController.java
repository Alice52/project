package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.UserApi;
import cn.edu.ntu.seckill.email.IMailSenderService;
import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.exception.UserException;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.service.IUserService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

  @Resource private HttpServletRequest httpServletRequest;
  @Resource private RedisTemplate redisTemplate;

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

    validateValidationCode(userVO.getValidationCode());

    userVO.setRegisterDate(LocalDateTime.now());
    String uuid = userService.register(userVO);

    return buildJson("id", uuid);
  }

  /**
   * Validate validation code.<br>
   *
   * @param codeFromVO
   */
  private void validateValidationCode(String codeFromVO) {

    if (StrUtil.isBlank(codeFromVO)) {
      throw new UserException().new InvalidValidationCodeException("Please fill validation code.");
    }

    Object codeFromRedis = httpServletRequest.getSession().getAttribute("validation code");
    if (ObjectUtil.isNull(codeFromRedis)) {
      throw new UserException()
      .new InvalidValidationCodeException("Validation code is expire, please obtain and try again.");
    }

    String code = String.valueOf(codeFromRedis);
    if (!StrUtil.equals(code, codeFromVO)) {
      throw new UserException()
      .new InvalidValidationCodeException("Invalid validation code, please try again.");
    }
  }

  /**
   * This api can be implemented by using redis directly.
   *
   * @param email
   * @return
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

    // this operation has nothing with spring session.
    /* redisTemplate.opsForValue().set("validation code1", randomNumbers, 60, TimeUnit.SECONDS);*/
    httpServletRequest.getSession().setAttribute("validation code", randomNumbers);

    return buildJson("success", true);
  }
}
