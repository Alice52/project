package cn.edu.ntu.project.seckill.api.configuration;

import cn.edu.ntu.seckill.redis.starter.autoconfigure.key.KeyPrefix;

/**
 * @author zack <br>
 * @create 2020-06-30 22:52 <br>
 * @project seckill-backend <br>
 */
public enum RedisOrderKeyEnum implements KeyPrefix {
  ORDER_USER("order:user:");

  private int expireSeconds;
  private String prefix;

  RedisOrderKeyEnum(String prefix) {
    this.prefix = prefix;
  }

  RedisOrderKeyEnum(int expireSeconds, String prefix) {
    this.expireSeconds = expireSeconds;
    this.prefix = prefix;
  }

  /**
   * default expire time is 0, never expire
   *
   * @return 0
   */
  @Override
  public int expireSeconds() {
    return 0;
  }

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }

  public int getExpireSeconds() {
    return expireSeconds;
  }
}
