package cn.edu.ntu.projectname.configuarion;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * config swagger for api. Uri: http://localhost:8008/swagger-ui.html <br>
 * link: https://www.jianshu.com/p/4539e312ce87<br>
 * link2:
 * https://www.ibm.com/developerworks/cn/java/j-using-swagger-in-a-spring-boot-project/index.html
 * <br>
 *
 * @author zack <br>
 * @create 2020-04-27 11:45 <br>
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
    // release relevant js
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public Docket createRestApi() {

    List<ResponseMessage> responseMessageList = new ArrayList<>();
    responseMessageList.add(new ResponseMessageBuilder().code(404).message("Not Found").build());
    responseMessageList.add(
        new ResponseMessageBuilder()
            .code(400)
            .message("Internal Error")
            .responseModel(new ModelRef("ErrorResponse"))
            .build());
    responseMessageList.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        // .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, responseMessageList)
        .globalResponseMessage(RequestMethod.POST, responseMessageList)
        .globalResponseMessage(RequestMethod.PUT, responseMessageList)
        .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
        // Extensibility mechanism to add a servlet path mapping,
        // if there is one, to the apis base path.
        // .pathMapping("/")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        // .apis(RequestHandlerSelectors.basePackage("cn.edu.ntu.boot.swagger2.controller"))
        .paths(PathSelectors.any())
        .build()
        .globalOperationParameters(parameter());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Api Document")
        .description("Api Document Description")
        .termsOfServiceUrl("https://github.com/Alice52/project/")
        .version("1.0")
        .build();
  }

  private List<Parameter> parameter() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(
        new ParameterBuilder()
            .name("token")
            .description("Api Token")
            .modelRef(new ModelRef("String"))
            .parameterType("header")
            .required(false)
            .build());

    return parameters;
  }
}
