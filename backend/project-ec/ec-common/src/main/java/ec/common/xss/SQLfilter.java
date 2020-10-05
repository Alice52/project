/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * <p>https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package ec.common.xss;

import cn.hutool.core.util.StrUtil;
import ec.common.utils.EcException;

import java.util.Arrays;

/**
 * filter sql str due to inject.
 *
 * @author zack <br>
 * @create 2020-10-05 15:35 <br>
 * @project project-ec <br>
 */
public class SQLfilter {

  /**
   * SQL inject
   *
   * @param str input string
   */
  public static String sqlInject(String str) {
    if (StrUtil.isBlank(str)) {
      return null;
    }

    // replace chars of ' | " | ; | \
    str =
        StrUtil.replace(str, "'", "")
            .replace("\"", "")
            .replace(";", "")
            .replace("\\", "")
            .toLowerCase();

    // invalid chars
    String[] keywords = {
      "master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"
    };

    // throw an exception if contain sql keywords
    String finalStr = str;
    Arrays.stream(keywords)
        .forEach(
            x -> {
              if (finalStr.indexOf(x) != -1) {
                throw new EcException("Contain invalid chars");
              }
            });

    return str;
  }
}
