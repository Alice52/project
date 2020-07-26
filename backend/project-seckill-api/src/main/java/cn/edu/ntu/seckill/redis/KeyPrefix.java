package cn.edu.ntu.seckill.redis;

/**
 * @author zack <br>
 * @create 2020-07-24 22:22 <br>
 * @project project-seckill <br>
 */
public interface KeyPrefix {

  /**
   * set expire time.
   *
   * @return int
   */
  int expireSeconds();

  /**
   * set key prefix.
   *
   * @return String
   */
  String getPrefix();
}
