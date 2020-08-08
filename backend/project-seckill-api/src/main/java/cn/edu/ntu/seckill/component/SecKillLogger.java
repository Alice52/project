package cn.edu.ntu.seckill.component;

import cn.edu.ntu.seckill.constants.AppContextConstant;
import cn.edu.ntu.seckill.enumeration.LoggerEnum;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.level.Level;
import org.springframework.stereotype.Component;

/**
 * Customized Logger, it will log requestId.
 *
 * <p>But it will mass execute, due to log is always executed in LoggerEnum class.
 *
 * @author zack <br>
 * @create 2020-08-08 19:40 <br>
 * @project project-seckill <br>
 */
@Component
@Deprecated
public class SecKillLogger {

  void trace(String format, Object... arguments) {
    log(Level.TRACE, format, arguments);
  }

  public void trace(String str) {
    log(Level.TRACE, str);
  }

  public void debug(String format, Object... arguments) {
    log(Level.DEBUG, format, arguments);
  }

  public void debug(String str) {
    log(Level.DEBUG, str);
  }

  public void warn(String format, Object... arguments) {
    log(Level.WARN, format, arguments);
  }

  public void warn(String str) {
    log(Level.WARN, str);
  }

  public void info(String format, Object... arguments) {

    log(Level.INFO, format, arguments);
  }

  public void info(String str) {
    log(Level.INFO, str);
  }

  public void error(String format, Object... arguments) {
    log(Level.ERROR, format, arguments);
  }

  public void error(String str) {
    log(Level.ERROR, str);
  }

  private void log(Level level, String str) {
    if (AppContext.contain(AppContextConstant.REQUEST_ID)) {
      Object requestId = AppContext.getByKey(AppContextConstant.REQUEST_ID, String.class);
      StrUtil.addPrefixIfNot(str, AppContextConstant.REQUEST_PREFIX + requestId + StrUtil.SPACE);
    }

    LoggerEnum.valueOf(level.toString()).log(str);
  }

  private void log(Level level, String format, Object... arguments) {
    Object[] params = arguments;
    if (AppContext.contain(AppContextConstant.REQUEST_ID)) {
      format = StrUtil.addPrefixIfNot(format, AppContextConstant.REQUEST_PREFIX);
      Object requestId = AppContext.getByKey(AppContextConstant.REQUEST_ID, String.class);
      params = ArrayUtil.insert(params, 0, requestId);
    }
    LoggerEnum.valueOf(level.toString()).log(format, params);
  }
}
