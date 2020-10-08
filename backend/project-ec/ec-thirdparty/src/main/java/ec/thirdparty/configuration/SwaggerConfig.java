package ec.thirdparty.configuration;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author zack <br>
 * @create 2020-10-05 19:11 <br>
 * @project project-ec <br>
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(
    prefix = "swagger2",
    value = {"enable"},
    havingValue = "true")
public class SwaggerConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // release swagger
    registry
        .addResourceHandler("/swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
    // release relevant js
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public Docket createRestAppApi() {
    return getDocket()
        .groupName("ec-third-party")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .paths(PathSelectors.any())
        .build();
  }

  private Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).securitySchemes(security());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("EC-THIRD-PARTY")
        .description("EC-THIRD-PARTY 文档")
        .termsOfServiceUrl("https://github.com/Alice52/project/")
        .version("3.0.0")
        .build();
  }

  private List<ApiKey> security() {
    return newArrayList(new ApiKey("token", "token", "header"));
  }
}
