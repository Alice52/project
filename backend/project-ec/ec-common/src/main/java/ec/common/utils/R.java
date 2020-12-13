package ec.common.utils;

import cn.hutool.core.util.ObjectUtil;
import ec.common.error.ErrorMessageEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-10-05 15:35 <br>
 * @project project-ec <br>
 */
public class R extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;

  public R() {
    put("code", 0);
    put("msg", "success");
  }

  public static R error(ErrorMessageEnum errorMessageEnum) {
    R r = new R();
    r.put("code", errorMessageEnum.getCode());
    r.put("msg", errorMessageEnum.getMsg());
    return r;
  }

  public static R error(int code, String msg) {
    R r = new R();
    r.put("code", code);
    r.put("msg", msg);
    return r;
  }

  public static R error() {
    return error(500, "Internal Error, Please Concat Super Admin");
  }

  public static R error(String msg) {
    return error(500, msg);
  }

  public static R ok() {
    return new R();
  }

  public static R ok(String msg) {
    R r = new R();
    r.put("msg", msg);
    return r;
  }

  public static R ok(Map<String, Object> map) {
    R r = new R();
    r.putAll(map);
    return r;
  }

  @Override
  public R put(String key, Object value) {
    super.put(key, value);
    return this;
  }

  public Integer getCode() {
    Object code = this.get("code");
    if (ObjectUtil.isNotNull(code) && code instanceof Integer) {
      return (Integer) code;
    }

    return null;
  }
}
