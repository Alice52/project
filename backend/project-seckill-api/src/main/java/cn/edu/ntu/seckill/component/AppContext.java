package cn.edu.ntu.seckill.component;

import cn.hutool.core.util.ObjectUtil;

import java.util.HashMap;
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

  public static void upsertByKey(String prefix, String key, Object value) {
    key = prefix + key;
    getAppHolder().put(key, value);
  }

  @Deprecated
  public static void upsertByKey(String key, Object value) {
    getAppHolder().put(key, value);
  }

  public static <T> T getByKey(String prefix, String key, Class<T> clazz) {
    key = prefix + key;
    Object value = getAppHolder().get(key);
    if (ObjectUtil.isNull(value)) {
      return null;
    }

    return (T) value;
  }

  @Deprecated
  public static <T> T getByKey(String key, Class<T> clazz) {
    Object value = getAppHolder().get(key);
    if (ObjectUtil.isNull(value)) {
      return null;
    }

    return (T) value;
  }

  public static void removeByKey(String prefix, String key) {
    if (contain(prefix, key)) {
      remove(key);
    }
  }

  @Deprecated
  public static void removeByKey(String key) {
    if (contain(key)) {
      remove(key);
    }
  }

  public static boolean contain(String prefix, String key) {
    key = prefix + key;
    Map<String, Object> objectMap = getAppHolder();

    if (objectMap.containsKey(key)) {
      return true;
    }

    return false;
  }

  @Deprecated
  public static boolean contain(String key) {
    Map<String, Object> objectMap = getAppHolder();

    if (objectMap.containsKey(key)) {
      return true;
    }

    return false;
  }

  /**
   * Get appHolder value of map.<br>
   * If not exist, it will be initialized.
   *
   * @return
   */
  private static Map<String, Object> getAppHolder() {
    return initMapIfNeed(appHolder.get());
  }

  private static void remove(String key) {
    appHolder.get().remove(key);
  }

  private static Map<String, Object> initMapIfNeed(Map<String, Object> objectMap) {
    if (ObjectUtil.isNotNull(objectMap)) {
      return objectMap;
    } else {
      return initAppHolder();
    }
  }

  private static Map<String, Object> initAppHolder() {
    appHolder.set(new HashMap<>(16));

    return appHolder.get();
  }
}
