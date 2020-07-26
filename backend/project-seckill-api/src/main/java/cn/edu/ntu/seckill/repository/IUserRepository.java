package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zack <br>
 * @create 2020-07-26 19:20 <br>
 * @project project-seckill <br>
 */
@Mapper
public interface IUserRepository {
  /**
   * Create a new user.
   *
   * @param userPO
   * @return user id.
   */
  void create(UserPO userPO);
}
