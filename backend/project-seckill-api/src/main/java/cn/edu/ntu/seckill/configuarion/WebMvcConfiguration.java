package cn.edu.ntu.seckill.configuarion;

import cn.edu.ntu.seckill.interceptor.RequestInterceptor;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-08-05 22:39 <br>
 * @project project-seckill <br>
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Resource private RequestInterceptor accessInterceptor;
  @Resource private UserArgumentResolver userArgumentResolver;

  /**
   * in this class will handle user model, validate token, <br>
   * then we can get user model from controller parameter.
   *
   * @param argumentResolvers
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(userArgumentResolver);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {}

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(accessInterceptor);
  }

  @Bean
  public WebServerFactoryCustomizer embeddedServletContainerCustomizer() {
    return (factory) -> {
      ((TomcatServletWebServerFactory) factory)
          .addConnectorCustomizers(
              connector -> {
                Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
                // set keep-alive timeout: 30s
                protocol.setKeepAliveTimeout(30000);
                // client request gt 10000, will close keepalive connection
                protocol.setMaxKeepAliveRequests(10000);
              });
    };
  }
}
