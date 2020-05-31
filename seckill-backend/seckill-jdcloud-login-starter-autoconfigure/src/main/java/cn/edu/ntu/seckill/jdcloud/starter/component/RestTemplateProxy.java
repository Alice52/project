package cn.edu.ntu.seckill.jdcloud.starter.component;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

/**
 * @author zack <br>
 * @create 2020-05-30 22:29 <br>
 * @project seckill-backend <br>
 */
@Component
public class RestTemplateProxy extends RestTemplate {

  @Override
  public <T> ResponseExtractor<ResponseEntity<T>> responseEntityExtractor(Type responseType) {
    return new NoExceptionRestTemplateResponseEntityExtractor<>(
        responseType, getMessageConverters());
  }
}
