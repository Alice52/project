package cn.edu.ntu.seckill.redis.starter.autoconfigure.key;

/**
 * @author zack <br>
 * @create 2020-05-17 17:56 <br>
 * @project seckill-backend <br>
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
