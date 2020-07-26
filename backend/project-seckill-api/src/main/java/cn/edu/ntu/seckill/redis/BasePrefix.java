package cn.edu.ntu.seckill.redis;

/**
 * @author zack <br>
 * @create 2020-07-24 22:22 <br>
 * @project project-seckill <br>
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
