package cn.edu.ntu.seckill.component;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;

import java.util.Map;

/**
 * This is thread isolation for each specify info, such as request id.
 *
 * <p>Please remove useless keywords immediately.
 *
 * @author zack <br>
 * @create 2020-08-05 21:41 <br>
 * @project project-seckill <br>
 */
public class AppContext {
  private static ThreadLocal<Map<String, Object>> appHolder = new ThreadLocal<>();

  private static Map<String, Object> getAppHolder() {
    Map<String, Object> objectMap = appHolder.get();

    Assert.notNull(objectMap, "The thread local instance must not be null");
    return objectMap;
  }

  public static void initAppHolder(Map<String, Object> map) throws Exception {

    appHolder.set(map);
  }

  public static void upsertByKey(String key, Object value) {
    Map<String, Object> objectMap = getAppHolder();

    objectMap.put(key, value);
  }

  public static <T> T getByKey(String key, Class<T> clazz) {
    Map<String, Object> objectMap = getAppHolder();
    Object value = objectMap.get(key);

    Assert.notNull(value, "The class {} must not be null", key);

    return (T) value;
  }

  public static void removeByKey(String key) {
    Map<String, Object> objectMap = appHolder.get();
    if (ObjectUtil.isNotNull(objectMap) && objectMap.containsKey(key)) {
      objectMap.remove(key);
    }
  }
}
