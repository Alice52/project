package cn.edu.ntu.seckill.redis.starter.autoconfigure.key;

/**
 * @author zack <br>
 * @create 2020-05-17 19:17 <br>
 * @project seckill-backend <br>
 */
public class UserKey extends BasePrefix {

  private UserKey(String prefix) {
    super(prefix);
  }

  private UserKey(int expireSeconds, String prefix) {
    super(expireSeconds, prefix);
  }

  public static UserKey userKeyId = new UserKey("id");
  public static UserKey userKeyName = new UserKey(50, "name");
}
