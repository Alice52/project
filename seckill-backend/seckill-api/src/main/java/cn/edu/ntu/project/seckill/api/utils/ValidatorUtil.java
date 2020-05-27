package cn.edu.ntu.project.seckill.api.utils;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zack <br>
 * @create 2020-05-26 21:54 <br>
 * @project seckill-backend <br>
 */
public class ValidatorUtil {

  private static final Pattern MOBILE_PATTERN = Pattern.compile("^[1]\\d{10}$");

  public static boolean isMobile(String src) {
    if (StrUtil.isEmpty(src)) {
      return false;
    }
    Matcher m = MOBILE_PATTERN.matcher(src);
    return m.matches();
  }
}
