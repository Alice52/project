package cn.edu.ntu.seckill.jdcloud.starter.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-05-30 22:26 <br>
 * @project seckill-backend <br>
 */
public class NoExceptionRestTemplateResponseEntityExtractor<T>
    implements ResponseExtractor<ResponseEntity<T>> {
  private static final Logger log =
      LoggerFactory.getLogger(NoExceptionRestTemplateResponseEntityExtractor.class);
  private final HttpMessageConverterExtractor<T> delegate;

  NoExceptionRestTemplateResponseEntityExtractor(
      Type responseType, List<HttpMessageConverter<?>> converters) {
    if (responseType != null && Void.class != responseType) {
      this.delegate = new HttpMessageConverterExtractor<>(responseType, converters);
    } else {
      this.delegate = null;
    }
  }

  @Override
  public ResponseEntity<T> extractData(ClientHttpResponse response) throws IOException {
    if (this.delegate != null) {
      T body = null;
      try {
        // TODO: read response firstly, then call super method

        /*
        InputStream inputStream = response.getBody();
        BufferedInputStream buffInputStream = new BufferedInputStream(inputStream);
        buffInputStream.mark(inputStream.available() + 1);
        String content = new String(ByteStreams.toByteArray(buffInputStream), Charsets.UTF_8);
        buffInputStream.reset();
        */
        body = this.delegate.extractData(response);
      } catch (RuntimeException e) {
        return new ResponseEntity(null, response.getHeaders(), response.getStatusCode());
      }
      return new ResponseEntity<>(body, response.getHeaders(), response.getStatusCode());
    } else {
      return new ResponseEntity<>(response.getHeaders(), response.getStatusCode());
    }
  }
}
