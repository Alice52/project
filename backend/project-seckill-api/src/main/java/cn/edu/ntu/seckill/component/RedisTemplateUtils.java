package cn.edu.ntu.seckill.component;

import cn.edu.ntu.seckill.redis.KeyPrefix;
import cn.edu.ntu.seckill.utils.RedisKeyUtils;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This utils is just cache data in redis, and get from redis first. <br>
 * Notice different between <code>cn.edu.ntu.seckill.component.CacheUtils</code>
 *
 * @author zack <br>
 * @create 2020-08-30 12:07 <br>
 * @project project-seckill <br>
 */
@Component
@Slf4j
public class RedisTemplateUtils {
  @Resource private RedisTemplate redisTemplate;

  public boolean hasKey(KeyPrefix prefix, String maker, String... key) {
    Boolean hasKey = redisTemplate.hasKey(RedisKeyUtils.buildKey(prefix, maker, key));

    return hasKey;
  }

  public long increment(long delta, KeyPrefix prefix, String maker, String... key) {
    long result =
        redisTemplate.opsForValue().increment(RedisKeyUtils.buildKey(prefix, maker, key), delta);

    return result;
  }

  public <E> void set(E e, KeyPrefix prefix, String maker, String... key) {
    String realKey = RedisKeyUtils.buildKey(prefix, maker, key);
    set(e, realKey, 30 * 6, TimeUnit.DAYS);
  }

  public <E> void set(
      E e, final long seconds, final TimeUnit unit, KeyPrefix prefix, String maker, String... key) {
    String realKey = RedisKeyUtils.buildKey(prefix, maker, key);
    set(e, realKey, seconds, unit);
  }

  private void set(Object obj, String realKey, final long timeout, final TimeUnit unit) {
    Optional.ofNullable(obj)
        .ifPresent(x -> redisTemplate.opsForValue().set(realKey, obj, timeout, unit));
  }

  public boolean expire(KeyPrefix prefix, String key, final long timeout, final TimeUnit unit) {

    String realKey = RedisKeyUtils.buildKey(prefix, key);
    return redisTemplate.expire(realKey, timeout, unit);
  }

  public <E> E get(Class<E> clazz, KeyPrefix prefix, String maker, String... key) {

    String realKey = RedisKeyUtils.buildKey(prefix, maker, key);
    return get(realKey);
  }

  public <E> E get(Class<E> clazz, KeyPrefix prefix, String key) {

    String realKey = RedisKeyUtils.buildKey(prefix, key);
    return get(realKey);
  }

  private <E> E get(String realKey) {

    Object object = redisTemplate.opsForValue().get(realKey);

    if (ObjectUtil.isNotNull(object)) {
      E e = null;
      try {
        e = (E) object;
      } catch (Exception ex) {
        log.warn("cast object: {} to type: {} error: {}", object, e.getClass(), ex);
      }

      return e;
    }

    return null;
  }

  public void removeAll(KeyPrefix prefix, String maker) {
    Set<String> keys = redisTemplate.keys(RedisKeyUtils.buildDeleteKey(prefix, maker, "*"));
    Optional.ofNullable(keys).ifPresent(x -> redisTemplate.delete(keys));
  }

  /**
   * Remove all cache keys found by makers.
   *
   * @param prefix
   * @param key
   * @param keys
   */
  public void remove(KeyPrefix prefix, String key, String... keys) {

    Arrays.stream(ArrayUtil.append(keys, key))
        .forEach(
            x -> {
              Set<String> keySet = redisTemplate.keys(RedisKeyUtils.buildDeleteKey(prefix, x));
              Optional.ofNullable(keySet).ifPresent(y -> redisTemplate.delete(keySet));
            });
  }
}
