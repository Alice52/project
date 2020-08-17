package cn.edu.ntu.seckill.redis;

/**
 * @author zack <br>
 * @create 2020-08-17 21:59 <br>
 * @project project-seckill <br>
 */
public enum RedisSeckillGoodsKeyEnum implements KeyPrefix {
  SECKILL_GOODS("seckill-plus:seckill_goods");

  private String prefix;

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }

    RedisSeckillGoodsKeyEnum(String prefix) {
    this.prefix = prefix;
  }
}
