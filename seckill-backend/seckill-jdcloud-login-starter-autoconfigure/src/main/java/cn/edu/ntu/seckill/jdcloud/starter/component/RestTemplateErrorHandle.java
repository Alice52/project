package cn.edu.ntu.seckill.jdcloud.starter.component;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * @author zack <br>
 * @create 2020-05-30 22:08 <br>
 * @project seckill-backend <br>
 */
@Component
public class RestTemplateErrorHandle extends DefaultResponseErrorHandler {
  @Override
  public void handleError(ClientHttpResponse response) {}
}
