package cn.edu.ntu.seckill.service;

/**
 * This interface is ued and just used by LoggerEnum.<br>
 * And LoggerEnum used by SeckillLogger, which will log the requestId.
 *
 * @author zack <br>
 * @create 2020-08-08 20:17 <br>
 * @project project-seckill <br>
 */
@Deprecated
public interface ILogger {

  /**
   * Log variable parameter.
   *
   * @param format
   * @param params
   */
  void log(String format, Object... params);

  /**
   * Log specify string variable.
   *
   * @param str
   */
  void log(String str);
}
