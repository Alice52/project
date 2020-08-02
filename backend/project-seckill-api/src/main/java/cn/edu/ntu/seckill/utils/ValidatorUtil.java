package cn.edu.ntu.seckill.utils;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zack <br>
 * @create 2020-08-01 15:12 <br>
 * @project project-seckill <br>
 */
public final class ValidatorUtil {

  private static final Pattern MOBILE_PATTERN = Pattern.compile("^[1]\\d{10}$");

  public static boolean validateMobile(String phoneNumber) {
    if (StrUtil.isEmpty(phoneNumber)) {
      return false;
    }
    Matcher m = MOBILE_PATTERN.matcher(phoneNumber);
    return m.matches();
  }
}
