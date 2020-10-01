/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * <p>https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package io.renren.config;

import io.renren.common.annotation.AppApi;
import io.renren.common.annotation.JobApi;
import io.renren.common.annotation.OssApi;
import io.renren.common.annotation.SysApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableSwagger2
@ConditionalOnProperty(
    prefix = "swagger2",
    value = {"enable"},
    havingValue = "true")
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket createRestAppApi() {
    return getDocket()
        .groupName("ec-admin-app")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(AppApi.class))
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public Docket createRestJobApi() {
    return getDocket()
        .groupName("ec-admin-job")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(JobApi.class))
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public Docket createRestOssApi() {
    return getDocket()
        .groupName("ec-admin-oss")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(OssApi.class))
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public Docket createRestSysApi() {
    return getDocket()
        .groupName("ec-admin-sys")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(SysApi.class))
        .paths(PathSelectors.any())
        .build();
  }

  private Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).securitySchemes(security());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("EC-ADMIN")
        .description("EC-ADMIN 文档")
        .termsOfServiceUrl("https://github.com/Alice52/project/")
        .version("3.0.0")
        .build();
  }

  private List<ApiKey> security() {
    return newArrayList(new ApiKey("token", "token", "header"));
  }
}
