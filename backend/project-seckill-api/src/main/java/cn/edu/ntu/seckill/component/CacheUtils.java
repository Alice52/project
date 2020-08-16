package cn.edu.ntu.seckill.component;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zack <br>
 * @create 2020-08-16 23:02 <br>
 * @project project-seckill <br>
 */
@Slf4j
@Component
public class CacheUtils<T> {

  /** cache in memory 5 seconds, and max cache number is 100 */
  private LFUCache<String, Object> memoryCache = CacheUtil.newLFUCache(100, 5 * 1000);

  @Resource private RedisTemplate redisTemplate;

  public void cache(T bo, String realKey, long seconds) {
    Optional.ofNullable(bo)
        .ifPresent(
            x -> {
              memoryCache.put(realKey, bo);
              redisTemplate.opsForValue().set(realKey, bo, seconds, TimeUnit.SECONDS);
            });
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
}
