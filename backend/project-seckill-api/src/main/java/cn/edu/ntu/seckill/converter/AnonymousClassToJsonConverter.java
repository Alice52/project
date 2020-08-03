package cn.edu.ntu.seckill.converter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import org.slf4j.helpers.MessageFormatter;

import java.util.ArrayList;
import java.util.List;

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
      if (null != event || null != event.getArgumentArray()) {
        List<Object> array = new ArrayList<>();
        for (Object argument : event.getArgumentArray()) {
          if (ObjectUtil.isBasicType(argument)) {
            array.add(argument);
          } else {
            array.add(JSONUtil.toJsonPrettyStr(argument));
          }
        }

        return MessageFormatter.arrayFormat(event.getMessage(), array.toArray()).getMessage();
      }
      return super.convert(event);

    } catch (Exception e) {
      return event.getMessage();
    }
  }
}
