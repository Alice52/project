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
  @Deprecated
  default int expireSeconds() {
    return 0;
  };

  /**
   * set key prefix.
   *
   * @return String
   */
  String getPrefix();
}
