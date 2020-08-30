package cn.edu.ntu.seckill.redis;

import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-29 21:12 <br>
 * @project project-seckill <br>
 */
public enum RedisCommonEnum implements KeyPrefix {
  ALL,
  VERIFY_CODE("verify_code");

  private String prefix = KeyPrefix.prefix;

  RedisCommonEnum() {}

  @Override
  public String getPrefix() {
    String className = getClass().getSimpleName();
    return className + StrUtil.COLON + prefix;
  }

  RedisCommonEnum(String prefix) {
    this.prefix += prefix;
  }
}
