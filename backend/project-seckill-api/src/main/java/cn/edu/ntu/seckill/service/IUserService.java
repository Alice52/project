package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.model.bo.UserBO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

/**
 * @author zack <br>
 * @create 2020-07-26 19:06 <br>
 * @project project-seckill <br>
 */
@Validated
public interface IUserService {

  /**
   * Register a new user.
   *
   * @param userVO
   * @return
   * @throws UnsupportedEncodingException
   */
  @Transactional(
      propagation = Propagation.REQUIRED,
      isolation = Isolation.READ_COMMITTED,
      rollbackFor = {Exception.class})
  String register(@NotNull @Param("userVO") UserVO userVO) throws UnsupportedEncodingException;

  /**
   * Change password.
   *
   * @param userBO
   * @param password
   * @return
   * @throws UnsupportedEncodingException
   */
  boolean changePassword(@Valid UserBO userBO, @NotBlank String password)
      throws UnsupportedEncodingException;

  /**
   * User login by password and responed a token.
   *
   * @param userId
   * @param password
   * @return
   * @throws UnsupportedEncodingException
   */
  String login(@NotBlank String userId, @NotBlank String password)
      throws UnsupportedEncodingException;

  /**
   * Query user by userId.
   *
   * @param userId
   * @return UserVO
   */
  UserVO getByUserId(@NotBlank String userId);
}
