package cn.edu.ntu.seckill.redis.starter.autoconfigure.key;

/**
 * @author zack <br>
 * @create 2020-05-17 18:00 <br>
 * @project seckill-backend <br>
 */
public abstract class BasePrefix implements KeyPrefix {

  private int expireSeconds;

  private String prefix;

  /**
   * 0 is represent never expire.
   *
   * @param prefix
   */
  public BasePrefix(String prefix) {
    this(0, prefix);
  }

  public BasePrefix(int expireSeconds, String prefix) {
    this.expireSeconds = expireSeconds;
    this.prefix = prefix;
  }

  @Override
  public int expireSeconds() {
    return expireSeconds;
  }

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }
}
