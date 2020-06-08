package cn.edu.ntu.project.seckill.api.repository;

import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zack <br>
 * @create 2020-06-08 19:42 <br>
 * @project seckill-backend <br>
 */
@Mapper
public interface IUserRepository {

  /**
   * Get seckill user by nickname.
   *
   * @param nickname
   * @return SeckillUser
   */
  SeckillUser getByNickname(@Param("nickname") String nickname);

  /**
   * Insert user to database.
   *
   * @param user
   * @return uuid
   */
  int insert(@Param("seckillUser") SeckillUser user);

  /**
   * validate login by username and password.
   *
   * @param username
   * @param password
   * @return
   */
  SeckillUser validateLogin(@Param("name") String username, @Param("password") String password);
}
