package cn.edu.ntu.seckill.redis;

/**
 * Token is store in redis, and has no prefix of `userId`; <br>
 * <tr> - prefix:token:token-value<br>
 * Other user info cache in redis will has prefix of `userId`, which is same as other cached object.
 * <br>
 * <tr> - prefix:userId:marker:value<br>
 *
 * <p>Remove all info should include token and other user info.
 *
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
   * if prefix = token, then this.prefix = prefix; <br/>
   * else this.prefix += prefix;<br
   */
  RedisUserKeyEnum(String prefix) {
    this.prefix += prefix;
  }
}
