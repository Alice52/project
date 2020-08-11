package cn.edu.ntu.seckill.utils;

import cn.edu.ntu.seckill.redis.KeyPrefix;
import cn.edu.ntu.seckill.redis.RedisUserKeyEnum;
import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-09 16:46 <br>
 * @project project-seckill <br>
 */
public final class RedisKeyUtils {
  public static String buildDeleteKey(KeyPrefix prefix, String... params) {

    return buildKey(prefix, params);
  }

  public static String buildKey(KeyPrefix prefix, String... params) {

    String realKey = prefix.getPrefix();
    for (String param : params) {
      realKey = realKey + StrUtil.COLON + param;
    }

    return realKey;
  }


}
