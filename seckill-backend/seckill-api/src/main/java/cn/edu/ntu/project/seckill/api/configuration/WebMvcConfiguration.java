package cn.edu.ntu.project.seckill.api.configuration;

import cn.edu.ntu.project.seckill.api.annotation.descriptor.AccessLimitDescriptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-04-27 12:47 <br>
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Resource private AccessLimitDescriptor accessLimitDescriptor;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // release swagger
    registry
        .addResourceHandler("/swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    // release relevant js
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(accessLimitDescriptor);
  }
}
