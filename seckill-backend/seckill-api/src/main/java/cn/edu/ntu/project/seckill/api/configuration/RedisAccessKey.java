package cn.edu.ntu.project.seckill.api.configuration;

import cn.edu.ntu.seckill.redis.starter.autoconfigure.key.BasePrefix;

/**
 * @author zack <br>
 * @create 2020-07-01 22:58 <br>
 * @project seckill-backend <br>
 */
public class RedisAccessKey extends BasePrefix {

  private RedisAccessKey(int expireSeconds, String prefix) {
    super(expireSeconds, prefix);
  }

  public static RedisAccessKey withExpire(int expireSeconds) {
    return new RedisAccessKey(expireSeconds, "access:");
  }
}
