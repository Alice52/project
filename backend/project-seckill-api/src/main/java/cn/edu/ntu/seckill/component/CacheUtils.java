package cn.edu.ntu.seckill.component;

import cn.edu.ntu.seckill.redis.KeyPrefix;
import cn.edu.ntu.seckill.utils.RedisKeyUtils;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
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

// TODO: need refine the redis operation by RedisTemplateUtils.
/**
 * This utils will cache data in memory and redis, and get from memory first. <br>
 * Notice different between <code>cn.edu.ntu.seckill.component.RedisTemplateUtils</code>
 *
 * @author zack <br>
 * @create 2020-08-16 23:02 <br>
 * @project project-seckill <br>
 */
@Slf4j
@Component
public class CacheUtils<T> {

  /** cache in memory 5 seconds, and max cache number is 100 */
  private LFUCache<String, Object> memoryCache = CacheUtil.newLFUCache(100, 500);

  @Resource private RedisTemplate redisTemplate;

  public void set(final long seconds, T bo, KeyPrefix prefix, String id, String... key) {
    set(seconds, TimeUnit.SECONDS, bo, prefix, id, key);
  }

  public void set(
      final long seconds, final TimeUnit unit, T bo, KeyPrefix prefix, String id, String... key) {
    String realKey = RedisKeyUtils.buildKey(prefix, id, key);
    set(bo, realKey, seconds, unit);
  }

  public void set(T bo, String realKey, final long timeout, final TimeUnit unit) {
    Optional.ofNullable(bo)
        .ifPresent(
            x -> {
              memoryCache.put(realKey, bo);
              redisTemplate.opsForValue().set(realKey, bo, timeout, unit);
            });
  }

  public boolean expire(KeyPrefix prefix, String key, final long timeout, final TimeUnit unit) {

    String realKey = RedisKeyUtils.buildKey(prefix, key);
    return redisTemplate.expire(realKey, timeout, unit);
  }

  public T get(KeyPrefix prefix, String maker, String... key) {

    String realKey = RedisKeyUtils.buildKey(prefix, maker, key);
    return get(realKey);
  }

  public T get(KeyPrefix prefix, String key) {

    String realKey = RedisKeyUtils.buildKey(prefix, key);
    return get(realKey);
  }

  public T get(String realKey) {

    Object object = memoryCache.get(realKey);

    if (ObjectUtil.isNull(object)) {
      object = redisTemplate.opsForValue().get(realKey);
      Optional.ofNullable(object).ifPresent(x -> memoryCache.put(realKey, x));
    }

    if (ObjectUtil.isNotNull(object)) {
      T t = null;
      try {
        t = (T) object;
      } catch (ClassCastException e) {
        log.warn("cast object: {} to type: {} error", object, t.getClass());
      }

      return t;
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
              memoryCache.remove(x);
              Set<String> keySet = redisTemplate.keys(RedisKeyUtils.buildDeleteKey(prefix, x));
              Optional.ofNullable(keySet).ifPresent(y -> redisTemplate.delete(keySet));
            });
  }
}
