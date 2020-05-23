package cn.edu.ntu.seckill.redis.starter.autoconfigure.service;

import cn.edu.ntu.seckill.redis.starter.autoconfigure.key.KeyPrefix;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-05-17 17:45 <br>
 * @project seckill-backend <br>
 */
@Service
public class RedisService {

  @Resource JedisPool jedisPool;

  public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {

    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      String strVal = jedis.get(keyPrefix.getPrefix() + key);
      T value = String2Bean(strVal, clazz);
      return value;
    } finally {
      returnPool(jedis);
    }
  }

  public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {

    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      String str = Bean2String(value);

      if (StrUtil.isBlank(str)) {
        return false;
      }

      String realKey = keyPrefix.getPrefix() + key;
      int seconds = keyPrefix.expireSeconds();
      if (seconds <= 0) {
        jedis.set(realKey, str);
      } else {
        jedis.setex(realKey, seconds, str);
      }
      return true;

    } finally {
      returnPool(jedis);
    }
  }

  private void returnPool(Jedis jedis) {
    if (jedis != null) {
      jedis.close();
    }
  }

  private <T> T String2Bean(String strVal, Class<T> clazz) {

    if (StrUtil.isBlank(strVal) || clazz == null) {
      return null;
    }

    if (clazz == int.class
        || clazz == Integer.class
        || clazz == long.class
        || clazz == Long.class) {
      return (T) Long.valueOf(strVal);
    } else if (clazz == String.class) {
      return (T) strVal;
    } else {
      return JSON.toJavaObject(JSON.parseObject(strVal), clazz);
    }
  }

  private <T> String Bean2String(T value) {

    if (null == value) {
      return null;
    }

    Class<?> clazz = value.getClass();

    if (clazz == int.class
        || clazz == Integer.class
        || clazz == long.class
        || clazz == Long.class) {
      return String.valueOf(value);
    } else if (clazz == String.class) {
      return (String) value;
    } else {
      return JSON.toJSONString(value);
    }
  }
}
