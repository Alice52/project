package cn.edu.ntu.seckill.redis;

import cn.edu.ntu.seckill.constants.CommonConstant;

/**
 * @author zack <br>
 * @create 2020-07-24 22:22 <br>
 * @project project-seckill <br>
 */
public interface KeyPrefix {

  String prefix = CommonConstant.REDIS_PREFIX_PROJECT_NAME;;
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
