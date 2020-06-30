package cn.edu.ntu.seckill.redis.starter.autoconfigure.service;

import cn.edu.ntu.seckill.redis.starter.autoconfigure.key.KeyPrefix;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.utils.SecKillBeanUtils;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
      T value = SecKillBeanUtils.String2Bean(strVal, clazz);
      return value;
    } finally {
      returnPool(jedis);
    }
  }

  public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {

    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      String str = SecKillBeanUtils.Bean2String(value);

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

  /** 判断key是否存在 */
  public <T> boolean exists(KeyPrefix prefix, String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      // 生成真正的key
      String realKey = prefix.getPrefix() + key;
      return jedis.exists(realKey);
    } finally {
      returnPool(jedis);
    }
  }

  /** 删除 */
  public boolean delete(KeyPrefix prefix, String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      // 生成真正的key
      String realKey = prefix.getPrefix() + key;
      long ret = jedis.del(realKey);
      return ret > 0;
    } finally {
      returnPool(jedis);
    }
  }

  /** 增加值 */
  public <T> Long incr(KeyPrefix prefix, String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      // 生成真正的key
      String realKey = prefix.getPrefix() + key;
      return jedis.incr(realKey);
    } finally {
      returnPool(jedis);
    }
  }

  /** 减少值 */
  public <T> Long decr(KeyPrefix prefix, String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      // 生成真正的key
      String realKey = prefix.getPrefix() + key;
      return jedis.decr(realKey);
    } finally {
      returnPool(jedis);
    }
  }

  public boolean delete(KeyPrefix prefix) {
    if (prefix == null) {
      return false;
    }
    List<String> keys = scanKeys(prefix.getPrefix());
    if (keys == null || keys.size() <= 0) {
      return true;
    }
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.del(keys.toArray(new String[0]));
      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  public List<String> scanKeys(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      List<String> keys = new ArrayList<String>();
      String cursor = "0";
      ScanParams sp = new ScanParams();
      sp.match("*" + key + "*");
      sp.count(100);
      do {
        ScanResult<String> ret = jedis.scan(cursor, sp);
        List<String> result = ret.getResult();
        if (result != null && result.size() > 0) {
          keys.addAll(result);
        }
        // 再处理cursor
        cursor = ret.getCursor();
      } while (!cursor.equals("0"));
      return keys;
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }

  private void returnPool(Jedis jedis) {
    if (jedis != null) {
      jedis.close();
    }
  }
}
