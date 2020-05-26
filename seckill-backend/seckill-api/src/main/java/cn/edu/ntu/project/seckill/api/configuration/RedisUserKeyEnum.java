package cn.edu.ntu.project.seckill.api.configuration;

import cn.edu.ntu.seckill.redis.starter.autoconfigure.key.KeyPrefix;

/**
 * @author zack <br>
 * @create 2020-05-17 19:17 <br>
 * @project seckill-backend <br>
 */
public enum RedisUserKeyEnum implements KeyPrefix {
  USER_KEY_ID("id"),
  USER_KEY_NAME(50, "name") {
    @Override
    public int expireSeconds() {
      return 50;
    }
  };

  private int expireSeconds;
  private String prefix;

  private RedisUserKeyEnum(String prefix) {
    this.prefix = prefix;
  }

  private RedisUserKeyEnum(int expireSeconds, String prefix) {
    this.expireSeconds = expireSeconds;
    this.prefix = prefix;
  }

  @Override
  public int expireSeconds() {
    return 0;
  }

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }
}
