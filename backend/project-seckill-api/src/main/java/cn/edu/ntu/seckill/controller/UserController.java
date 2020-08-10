package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.UserApi;
import cn.edu.ntu.seckill.converter.UserConverter;
import cn.edu.ntu.seckill.email.IMailSenderService;
import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.exception.UserException;
import cn.edu.ntu.seckill.model.bo.UserBO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.service.IUserService;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
   * User login interface, and response a token.
   *
   * @param userId
   * @param password
   * @return
   * @throws UnsupportedEncodingException
   */
  @PostMapping(value = "/login")
  public JSON login(
      @NotBlank @RequestParam("userId") String userId,
      @NotBlank @RequestParam("password") String password)
      throws UnsupportedEncodingException {
    String token = userService.login(userId, password);

    return buildJson("token", token);
  }

  /**
   * Get user detail by token.
   *
   * <p>User in can be got from redis.<br>
   * If user change any info, we will upsert redis user.
   *
   * @param user
   * @return UserVO
   * @throws UnsupportedEncodingException
   */
  @GetMapping(value = "/view")
  public UserVO view(@ApiIgnore UserBO user) {

    return UserConverter.INSTANCE.bo2vo(user);

    /* return userService.getByUserId(user.getId());*/
  }

  /**
   * Change password.
   *
   * @param password
   * @param userBO
   * @return
   * @throws UnsupportedEncodingException
   */
  @PutMapping(value = "/change-password")
  public JSON changePassword(
      @NotBlank @RequestParam("password") String password, @ApiIgnore UserBO userBO)
      throws UnsupportedEncodingException {

    boolean b = userService.changePassword(userBO, password);

    return buildJson("success", b);
  }

  /**
   * Register a new user.
   *
   * @param userVO
   * @return
   * @throws UnsupportedEncodingException
   */
  @PostMapping(value = "/register")
  public JSON register(@Valid @RequestBody UserVO userVO) throws UnsupportedEncodingException {

    String codeFromVO = userVO.getValidationCode();
    if (StrUtil.isBlank(codeFromVO)) {
      throw new UserException().new InvalidValidationCodeException("Please fill validation code.");
    }

    Object codeFromRedis = httpServletRequest.getSession().getAttribute("validation code");
    userService.validateValidationCode(codeFromVO, codeFromRedis);

    userVO.setRegisterDate(LocalDateTime.now());
    String uuid = userService.register(userVO);

    return buildJson("id", uuid);
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
