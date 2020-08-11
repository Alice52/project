package cn.edu.ntu.seckill.redis;

/**
 * @author zack <br>
 * @create 2020-08-09 16:32 <br>
 * @project project-seckill <br>
 */
public enum RedisUserKeyEnum implements KeyPrefix {
  USER_TOKEN("seckill-plus:user:token");

  private String prefix;

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }

  RedisUserKeyEnum(String prefix) {
    this.prefix = prefix;
  }
}
