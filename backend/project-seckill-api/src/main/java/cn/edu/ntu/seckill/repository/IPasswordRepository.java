package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.bo.PasswordBO;
import cn.edu.ntu.seckill.model.po.PasswordPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020-08-09 15:30 <br>
 * @project project-seckill <br>
 */
@Validated
@Mapper
public interface IPasswordRepository {
  /**
   * Create a new user password record.
   *
   * @param passwordPO
   * @return user id.
   */
  void create(@Valid PasswordPO passwordPO);

  /**
   * // TODO: can add index in this column.<br>
   * Get by email.
   *
   * @param userId
   * @return UserPO
   */
  PasswordBO queryByUserId(@NotBlank @Param("userId") String userId);

  /**
   * Update password.
   *
   * @param userId
   * @param salt
   * @param password
   */
  void updatePassword(
      @NotBlank @Param("userId") String userId,
      @NotBlank @Param("salt") String salt,
      @NotBlank @Param("password") String password);
}
