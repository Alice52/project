package cn.edu.ntu.seckill.converter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.json.JSONUtil;
import org.slf4j.helpers.MessageFormatter;

import java.util.stream.Stream;

/**
 * // TODO: how to make it only log method called from custom code.
 *
 * @author zack <br>
 * @create 2020-07-21 23:51 <br>
 * @project project-seckill <br>
 */
public class AnonymousClassToJsonConverter extends MessageConverter {
  @Override
  public String convert(ILoggingEvent event) {
    try {
      return MessageFormatter.arrayFormat(
              event.getMessage(),
              Stream.of(event.getArgumentArray()).map(JSONUtil::toJsonStr).toArray())
          .getMessage();
    } catch (Exception e) {
      return event.getMessage();
    }
  }
}
