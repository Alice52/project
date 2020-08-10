package cn.edu.ntu.seckill.utils;

import cn.edu.ntu.seckill.redis.RedisUserKeyEnum;
import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-09 16:46 <br>
 * @project project-seckill <br>
 */
public final class RedisKeyUtils {
  public static String buildDeleteKey(RedisUserKeyEnum userToken, String... params) {

    return buildKey(userToken, params);
  }

  public static String buildKey(RedisUserKeyEnum userToken, String... params) {

    String realKey = userToken.getPrefix();
    for (String param : params) {
      realKey = realKey + StrUtil.COLON + param;
    }

    return realKey;
  }
}
