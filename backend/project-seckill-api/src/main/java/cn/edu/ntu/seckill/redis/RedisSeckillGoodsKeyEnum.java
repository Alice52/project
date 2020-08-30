package cn.edu.ntu.seckill.redis;

import cn.edu.ntu.seckill.constants.CommonConstant;
import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-17 21:59 <br>
 * @project project-seckill <br>
 */
public enum RedisSeckillGoodsKeyEnum implements KeyPrefix {
  ALL,
  SECKILL_GOODS("seckill_goods"),
  SECKILL_GOODS_OVER("seckill_goods_over"),
  SECKILL_GOODS_TOKEN("seckill_goods_token"),
  SECKILL_GOODS_THRESHOLDS("seckill_goods_threshold"),
  SECKILL_GOODS_STOCK("seckill_goods_stock");

  private String prefix = KeyPrefix.prefix;

  RedisSeckillGoodsKeyEnum() {}

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + StrUtil.COLON + prefix;
  }

  RedisSeckillGoodsKeyEnum(String prefix) {
    this.prefix += prefix;
  }
}
