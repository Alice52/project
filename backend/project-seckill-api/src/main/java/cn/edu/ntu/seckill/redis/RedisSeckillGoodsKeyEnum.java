package cn.edu.ntu.seckill.redis;

/**
 * @author zack <br>
 * @create 2020-08-17 21:59 <br>
 * @project project-seckill <br>
 */
public enum RedisSeckillGoodsKeyEnum implements KeyPrefix {
  SECKILL_GOODS("seckill-plus:seckill_goods"),
  SECKILL_GOODS_THRESHOLDS("seckill-plus:seckill_goods_threshold"),
  SECKILL_GOODS_STOCK("seckill-plus:seckill_goods_stock");

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
