package cn.edu.ntu.seckill.serializer;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * @author zack <br>
 * @create 2020-08-09 13:23 <br>
 * @project project-seckill <br>
 */
public class DateTimeJsonDeserializer extends JsonDeserializer<DateTime> {
  @Override
  public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {
    String dateString = jsonParser.readValueAs(String.class);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    return DateUtil.parse(dateString, timeFormatter);
  }
}
