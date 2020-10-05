package ec.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zack <br>
 * @create 2020-10-05 15:35 <br>
 * @project project-ec <br>
 */
public class DateUtils {
  /** 时间格式(yyyy-MM-dd) */
  public static final String DATE_PATTERN = "yyyy-MM-dd";
  /** 时间格式(yyyy-MM-dd HH:mm:ss) */
  public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  public static String format(Date date) {
    return format(date, DATE_PATTERN);
  }

  public static String format(Date date, String pattern) {
    if (date != null) {
      SimpleDateFormat df = new SimpleDateFormat(pattern);
      return df.format(date);
    }
    return null;
  }
}
