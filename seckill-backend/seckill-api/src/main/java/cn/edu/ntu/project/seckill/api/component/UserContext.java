package cn.edu.ntu.project.seckill.api.component;

import cn.edu.ntu.project.seckill.api.entities.SeckillUser;

/**
 * @author zack <br>
 * @create 2020-06-29 21:11 <br>
 * @project seckill-backend <br>
 */
public class UserContext {
  private static ThreadLocal<SeckillUser> userHolder = new ThreadLocal<>();

  public static void setUser(SeckillUser user) {
    userHolder.set(user);
  }

  public static SeckillUser getUser() {
    return userHolder.get();
  }
}
