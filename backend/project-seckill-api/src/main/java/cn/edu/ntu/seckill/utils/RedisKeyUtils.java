package cn.edu.ntu.seckill.utils;

import cn.edu.ntu.seckill.redis.RedisUserKeyEnum;
import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-09 16:46 <br>
 * @project project-seckill <br>
 */
public final class RedisKeyUtils {
  public static String buildKey(RedisUserKeyEnum userToken, String token) {

    return StrUtil.concat(true, userToken.getPrefix(), token);
  }
}
