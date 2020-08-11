package cn.edu.ntu.seckill.redis;

/**
 * @author zack <br>
 * @create 2020-08-11 22:46 <br>
 * @project project-seckill <br>
 */
public enum RedisGoodsKeyEnum implements KeyPrefix {
  GOODS("seckill-plus:goods");

  private String prefix;

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }

  RedisGoodsKeyEnum(String prefix) {
    this.prefix = prefix;
  }
}
