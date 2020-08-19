package cn.edu.ntu.seckill.utils;

import cn.edu.ntu.seckill.redis.KeyPrefix;
import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-09 16:46 <br>
 * @project project-seckill <br>
 */
public final class RedisKeyUtils {
  public static String buildDeleteKey(KeyPrefix prefix, String id, String... params) {

    return buildKey(prefix, id, params);
  }

  public static String buildKey(KeyPrefix prefix, String id, String... params) {

    String realKey = StrUtil.removeSuffix(StrUtil.format(prefix.getPrefix(), id), StrUtil.COLON);
    for (String param : params) {
      realKey = realKey + StrUtil.COLON + param;
    }

    return realKey;
  }
}
