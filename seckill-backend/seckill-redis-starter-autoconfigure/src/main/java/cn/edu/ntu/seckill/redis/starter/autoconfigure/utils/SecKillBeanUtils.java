package cn.edu.ntu.seckill.redis.starter.autoconfigure.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

/**
 * @author zack <br>
 * @create 2020-06-30 22:11 <br>
 * @project seckill-backend <br>
 */
public final class SecKillBeanUtils {

  public static <T> T String2Bean(String strVal, Class<T> clazz) {

    if (StrUtil.isBlank(strVal) || clazz == null) {
      return null;
    }

    if (clazz == int.class || clazz == Integer.class) {
      return (T) Integer.valueOf(strVal);
    } else if (clazz == long.class || clazz == Long.class) {
      return (T) Long.valueOf(strVal);
    } else if (clazz == String.class) {
      return (T) strVal;
    } else {
      return JSON.toJavaObject(JSON.parseObject(strVal), clazz);
    }
  }

  public static <T> String Bean2String(T value) {

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
