package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.exception.UserException;
import cn.edu.ntu.seckill.model.bo.UserBO;
import cn.edu.ntu.seckill.model.po.PasswordPO;
import cn.edu.ntu.seckill.model.po.UserPO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.redis.RedisUserKeyEnum;
import cn.edu.ntu.seckill.repository.IPasswordRepository;
import cn.edu.ntu.seckill.repository.IUserRepository;
import cn.edu.ntu.seckill.utils.RedisKeyUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author zack <br>
 * @create 2020-07-26 19:10 <br>
 * @project project-seckill <br>
 */
@Service
public class UserServiceImpl implements IUserService {

  @Resource private RedisTemplate redisTemplate;
  @Resource private IUserRepository userRepository;
  @Resource private IPasswordRepository passwordRepository;

  @Override
  public String login(String userId, String password) throws UnsupportedEncodingException {

    PasswordPO passwordPO = validateThenGetPasswordPO(userId);
    UserPO userPO = validateThenGetByUserId(userId);
    String salt = passwordPO.getSalt();
    String passwordFromVO = convertPassword(password, salt);

    if (!StrUtil.equals(passwordFromVO, passwordPO.getPassword())) {
      throw new UserException()
      .new InvalidPassword("Username or password is wrong, please check and retry again");
    }

    String token = IdUtil.fastSimpleUUID();
    redisTemplate
        .opsForValue()
        .set(RedisKeyUtils.buildKey(RedisUserKeyEnum.USER_TOKEN, token), userPO, 2, TimeUnit.HOURS);

    return token;
  }

  @Override
  public UserVO getByUserId(String userId) {

    UserPO userPO = validateThenGetByUserId(userId);
    UserVO userVO = convert2VO(userPO);

    return userVO;
  }

  @Override
  public String register(UserVO userVO) throws UnsupportedEncodingException {

    this.validateDuplicated(userVO.getEmail());

    UserPO userPO = convert2PO(userVO);
    userRepository.create(userPO);

    PasswordPO passwordPO = convert2PasswordPO(userVO);
    passwordPO.setUserId(userPO.getId());
    passwordRepository.create(passwordPO);

    return userPO.getId();
  }

  @Override
  public boolean changePassword(UserBO userBO, String password)
      throws UnsupportedEncodingException {

    PasswordPO passwordPO = validateThenGetPasswordPO(userBO.getId());
    String salt = IdUtil.fastSimpleUUID();
    password = convertPassword(password, salt);

    passwordRepository.updatePassword(userBO.getId(), salt, password);
    redisTemplate.delete(RedisKeyUtils.buildKey(RedisUserKeyEnum.USER_TOKEN, userBO.getToken()));

    return true;
  }

  /**
   * Validate and get PasswordPO info by user id.<br>
   * If user not exists, will throw an UserNotExistenceException.
   *
   * @param userId
   * @return
   */
  private PasswordPO validateThenGetPasswordPO(String userId) {
    PasswordPO passwordPO = passwordRepository.queryByUserId(userId);

    if (ObjectUtil.isNull(passwordPO)) {
      throw new UserException().new UserNotExistenceException("USER_ID", userId);
    }

    return passwordPO;
  }

  /**
   * Convert to PasswordPO from userVO.
   *
   * @param userVO
   * @return
   * @throws UnsupportedEncodingException
   */
  private PasswordPO convert2PasswordPO(@NotNull UserVO userVO)
      throws UnsupportedEncodingException {
    PasswordPO passwordPO = new PasswordPO();
    String salt = IdUtil.simpleUUID();
    passwordPO.setSalt(salt);
    passwordPO.setPassword(convertPassword(userVO.getPassword(), salt));
    return passwordPO;
  }

  //  /**
  //   * Call this method from register, @Transactional annotation will still valid.
  //   *
  //   * @param userPO
  //   * @param passwordPO
  //   * @throws UnsupportedEncodingException
  //   */
  //  private void createRegisterRecords(UserPO userPO, PasswordPO passwordPO)
  //      throws UnsupportedEncodingException {
  //
  //    userRepository.create(userPO);
  //  }

  /**
   * Validate user email duplicated.<br>
   * If email is duplicated, throw an UserAlreadyExistenceException.
   *
   * @param email
   */
  private void validateDuplicated(String email) {

    UserPO userPO = userRepository.queryByEmail(email);

    if (ObjectUtil.isNotNull(userPO)) {
      throw new UserException().new UserAlreadyExistenceException(email);
    }
  }

  /**
   * Validate and get user info by userId. <br>
   * If user not exists, will throw an UserNotExistenceException.
   *
   * @param userId
   * @return
   */
  private UserPO validateThenGetByUserId(String userId) {

    UserPO userPO = userRepository.queryByUserId(userId);

    if (ObjectUtil.isNull(userPO)) {
      throw new UserException().new UserNotExistenceException("USER_ID", userId);
    }

    return userPO;
  }

  /**
   * Validate and get user info by user email.<br>
   * If user not exists, will throw an UserNotExistenceException.
   *
   * @param email
   * @return
   */
  private UserPO validateThenGetByEmail(String email) {

    UserPO userPO = userRepository.queryByEmail(email);

    if (ObjectUtil.isNull(userPO)) {
      throw new UserException().new UserNotExistenceException("EMAIL", email);
    }

    return userPO;
  }

  /**
   * convert password to db data.
   *
   * @param password
   * @param salt
   * @return
   * @throws UnsupportedEncodingException
   */
  private static String convertPassword(String password, String salt)
      throws UnsupportedEncodingException {
    MD5 md5 = new MD5(salt.getBytes(CharsetUtil.UTF_8));
    return md5.digestHex(password);
  }

  /**
   * Convert PO to VO.
   *
   * @param userPO
   * @return
   */
  private static UserVO convert2VO(UserPO userPO) {

    UserVO userVO = new UserVO();
    BeanUtil.copyProperties(userPO, userVO);

    return userVO;
  }

  /**
   * Convert VO to PO.
   *
   * @param userVO
   * @return
   * @throws UnsupportedEncodingException
   */
  private static UserPO convert2PO(UserVO userVO) throws UnsupportedEncodingException {

    @Valid UserPO userPO = new UserPO();
    BeanUtil.copyProperties(userVO, userPO);
    userPO.setRegisteredDate(LocalDateTime.now());

    return userPO;
  }
}
