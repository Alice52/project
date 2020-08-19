package cn.edu.ntu.seckill.service.impl;

import cn.edu.ntu.seckill.component.CacheUtils;
import cn.edu.ntu.seckill.constants.UserConstant;
import cn.edu.ntu.seckill.converter.PasswordConverter;
import cn.edu.ntu.seckill.converter.UserConverter;
import cn.edu.ntu.seckill.exception.UserException;
import cn.edu.ntu.seckill.model.bo.PasswordBO;
import cn.edu.ntu.seckill.model.bo.UserBO;
import cn.edu.ntu.seckill.model.po.PasswordPO;
import cn.edu.ntu.seckill.model.po.UserPO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.redis.RedisUserKeyEnum;
import cn.edu.ntu.seckill.repository.IPasswordRepository;
import cn.edu.ntu.seckill.repository.IUserRepository;
import cn.edu.ntu.seckill.service.IUserService;
import cn.edu.ntu.seckill.utils.SecurityUtils;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author zack <br>
 * @create 2020-07-26 19:10 <br>
 * @project project-seckill <br>
 */
@Service
public class UserServiceImpl implements IUserService {

  @Resource private IUserRepository userRepository;
  @Resource private IPasswordRepository passwordRepository;
  @Resource CacheUtils<UserBO> cacheService;

  @Override
  public String login(String userId, String password) throws UnsupportedEncodingException {

    PasswordBO passwordBO = validateThenGetPasswordBO(userId);
    UserBO user = validateThenGetByUserId(userId);
    String passwordFromVO = SecurityUtils.convertPassword(password, passwordBO.getSalt());

    if (!StrUtil.equals(passwordFromVO, passwordBO.getPassword())) {
      throw new UserException()
      .new InvalidPassword("Username or password is wrong, please check and retry again");
    }

    cacheService.removeAll(RedisUserKeyEnum.ALL, user.getId());
    String token = user.getId() + StrUtil.COLON + IdUtil.fastSimpleUUID();
    cacheService.set(
        UserConstant.TOKEN_VALID_TIME, TimeUnit.HOURS, user, RedisUserKeyEnum.USER_TOKEN, token);

    return token;
  }

  @Override
  public UserVO getByUserId(String userId) {

    UserBO user = validateThenGetByUserId(userId);
    UserVO userVO = UserConverter.INSTANCE.bo2vo(user);

    return userVO;
  }

  @Override
  public void validateValidationCode(String codeFromVO, Object codeFromRedis) {
    if (ObjectUtil.isNull(codeFromRedis)) {
      throw new UserException()
      .new InvalidValidationCodeException(
          "Validation code is expire, please obtain and try again.");
    }

    String code = String.valueOf(codeFromRedis);
    if (!StrUtil.equals(code, codeFromVO)) {
      throw new UserException()
      .new InvalidValidationCodeException("Invalid validation code, please try again.");
    }
  }

  @Override
  public String register(UserVO userVO) throws UnsupportedEncodingException {

    this.validateDuplicated(userVO.getEmail());

    UserPO userPO = UserConverter.INSTANCE.vo2po(userVO);
    userRepository.create(userPO);

    PasswordPO passwordPO =
        PasswordConverter.INSTANCE.userVO2PasswordPO(userVO, IdUtil.fastSimpleUUID());
    passwordPO.setUserId(userPO.getId());
    passwordRepository.create(passwordPO);

    return userPO.getId();
  }

  @Override
  public boolean changePassword(UserBO user, String password) throws UnsupportedEncodingException {

    String salt = IdUtil.fastSimpleUUID();
    password = SecurityUtils.convertPassword(password, salt);

    passwordRepository.updatePassword(user.getId(), salt, password);
    cacheService.removeAll(RedisUserKeyEnum.ALL, user.getId());

    return true;
  }

  /**
   * Validate and get PasswordPO info by user id.<br>
   * If user not exists, will throw an UserNotExistenceException.
   *
   * @param userId
   * @return
   */
  private PasswordBO validateThenGetPasswordBO(String userId) {
    PasswordBO passwordBO = passwordRepository.queryByUserId(userId);

    if (ObjectUtil.isNull(passwordBO)) {
      throw new UserException().new UserNotExistenceException("USER_ID", userId);
    }

    return passwordBO;
  }

  /**
   * Call this method from register, @Transactional annotation will still valid.
   *
   * @param userPO
   * @param passwordPO
   * @throws UnsupportedEncodingException
   */
  private void createRegisterRecords(UserPO userPO, PasswordPO passwordPO)
      throws UnsupportedEncodingException {

    userRepository.create(userPO);
  }

  /**
   * Validate user email duplicated.<br>
   * If email is duplicated, throw an UserAlreadyExistenceException.
   *
   * @param email
   */
  private void validateDuplicated(String email) {

    UserBO userBO = userRepository.queryByEmail(email);

    if (ObjectUtil.isNotNull(userBO)) {
      throw new UserException().new UserAlreadyExistenceException(email);
    }
  }

  private UserBO validateThenGetByUserId(String userId) {
    return validateThenGetByUserId(userId, null);
  }

  /**
   * Validate and get user info by userId. <br>
   * If user not exists, will throw an UserNotExistenceException.
   *
   * @param userId
   * @return
   */
  private UserBO validateThenGetByUserId(String userId, String token) {

    if (StrUtil.isBlank(token)) {
      UserBO userBO = cacheService.get(RedisUserKeyEnum.USER_TOKEN, token);
      if (ObjectUtil.isNotNull(userBO)) {
        return userBO;
      }
    }

    UserBO userBO = userRepository.queryByUserId(userId);
    if (ObjectUtil.isNull(userBO)) {
      throw new UserException().new UserNotExistenceException("USER_ID", userId);
    }

    return userBO;
  }

  /**
   * Validate and get user info by user email.<br>
   * If user not exists, will throw an UserNotExistenceException.
   *
   * @param email
   * @return
   */
  private UserBO validateThenGetByEmail(String email) {

    UserBO user = userRepository.queryByEmail(email);

    if (ObjectUtil.isNull(user)) {
      throw new UserException().new UserNotExistenceException("EMAIL", email);
    }

    return user;
  }
}
