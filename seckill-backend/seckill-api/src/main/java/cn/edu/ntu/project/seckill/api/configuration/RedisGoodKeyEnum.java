package cn.edu.ntu.project.seckill.api.configuration;

import cn.edu.ntu.seckill.redis.starter.autoconfigure.key.KeyPrefix;

/**
 * @author zack <br>
 * @create 2020-06-30 20:46 <br>
 * @project seckill-backend <br>
 */
public enum RedisGoodKeyEnum implements KeyPrefix {
  GOODS_OVER("stock-over:"),
  GOODS_STOCK("stock:");

  private int expireSeconds;
  private String prefix;

  RedisGoodKeyEnum(String prefix) {
    this.prefix = prefix;
  }

  RedisGoodKeyEnum(int expireSeconds, String prefix) {
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
