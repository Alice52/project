package cn.edu.ntu.seckill.enumeration;

import cn.edu.ntu.seckill.service.ILogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2020-08-08 20:14 <br>
 * @project project-seckill <br>
 */
@Deprecated
@Slf4j
@Component
public enum LoggerEnum implements ILogger {
  TRACE() {
    @Override
    public void log(String str, Object... params) {

      log.trace(str, params);
    }

    @Override
    public void log(String str) {

      log.trace(str);
    }
  },
  DEBUG() {
    @Override
    public void log(String str, Object... params) {

      log.debug(str, params);
    }

    @Override
    public void log(String str) {

      log.debug(str);
    }
  },
  INFO() {
    @Override
    public void log(String str, Object... params) {

      log.info(str, params);
    }

    @Override
    public void log(String str) {

      log.info(str);
    }
  },
  ERROR() {
    @Override
    public void log(String str, Object... params) {

      log.error(str, params);
    }

    @Override
    public void log(String str) {

      log.error(str);
    }
  };
}
