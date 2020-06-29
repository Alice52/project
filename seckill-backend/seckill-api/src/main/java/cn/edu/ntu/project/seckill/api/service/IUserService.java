package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.entities.SeckillUser;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020-06-08 19:53 <br>
 * @project seckill-backend <br>
 */
public interface IUserService {

  /**
   * get user info by nickname.
   *
   * @param nickname
   * @return SeckillUser
   */
  SeckillUser getByNickname(@NotBlank String nickname);

  /**
   * Insert user info to database.
   *
   * @param user
   * @return uuid
   */
  int validateAndSave(@NotBlank SeckillUser user);

  /**
   * Login by username and password.
   *
   * @param user
   * @return boolean
   */
  boolean doLogin(@NotBlank SeckillUser user);

  /**
   * Get user by token.
   *
   * @param response
   * @param token
   * @return
   */
  SeckillUser getByToken(HttpServletResponse response, String token);
}
