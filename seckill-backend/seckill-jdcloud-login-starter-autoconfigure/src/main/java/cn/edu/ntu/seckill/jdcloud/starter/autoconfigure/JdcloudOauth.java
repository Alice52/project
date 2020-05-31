package cn.edu.ntu.seckill.jdcloud.starter.autoconfigure;

import cn.edu.ntu.seckill.jdcloud.starter.component.RestTemplateErrorHandle;
import cn.edu.ntu.seckill.jdcloud.starter.component.RestTemplateProxy;
import cn.edu.ntu.seckill.jdcloud.starter.properties.UserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-05-26 21:08 <br>
 * @project seckill-backend <br>
 */
@Configuration
@EnableConfigurationProperties(UserProperties.class)
@ComponentScan(basePackages = "cn.edu.ntu.seckill.jdcloud.starter")
public class JdcloudOauth {

  @Resource private RestTemplateErrorHandle restTemplateErrorHandle;

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplateProxy();
    restTemplate.setErrorHandler(restTemplateErrorHandle);
    return restTemplate;
  }
}
