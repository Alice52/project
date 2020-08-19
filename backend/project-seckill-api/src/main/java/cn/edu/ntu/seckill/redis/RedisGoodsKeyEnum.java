package cn.edu.ntu.seckill.redis;

import cn.edu.ntu.seckill.constants.CommonConstant;

/**
 * @author zack <br>
 * @create 2020-08-11 22:46 <br>
 * @project project-seckill <br>
 */
public enum RedisGoodsKeyEnum implements KeyPrefix {
  ALL,
  GOODS("goods");

  private String prefix = KeyPrefix.prefix;

  RedisGoodsKeyEnum() {}

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + ":" + prefix;
  }

  RedisGoodsKeyEnum(String prefix) {
    this.prefix = CommonConstant.REDIS_PREFIX_PROJECT_NAME + prefix;
  }
}
