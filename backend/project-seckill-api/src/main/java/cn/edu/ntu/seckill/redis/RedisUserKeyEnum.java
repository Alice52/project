package cn.edu.ntu.seckill.redis;

/**
 * @author zack <br>
 * @create 2020-08-09 16:32 <br>
 * @project project-seckill <br>
 */
public enum RedisUserKeyEnum implements KeyPrefix {
  ALL,
  USER_TOKEN("token");

  private String prefix = KeyPrefix.prefix;

  RedisUserKeyEnum() {}

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }

  /**
   * if prefix = token, then this.prefix = prefix;
   * else this.prefix += prefix;
   */
  RedisUserKeyEnum(String prefix) {
    this.prefix += prefix;
  }
}
