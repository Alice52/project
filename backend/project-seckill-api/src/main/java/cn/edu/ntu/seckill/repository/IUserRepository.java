package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020-07-26 19:20 <br>
 * @project project-seckill <br>
 */
@Validated
@Mapper
public interface IUserRepository {
  /**
   * Create a new user.
   *
   * @param userPO
   * @return user id.
   */
  void create(@Valid UserPO userPO);

  /**
   * // TODO: can add index in this column.<br>
   * Get by email.
   *
   * @param email
   * @return UserPO
   */
  UserPO queryByEmail(@NotBlank @Param("email") String email);
}
