// package cn.edu.ntu.project.seckill.api.configuration;

// import com.alibaba.druid.pool.DruidDataSource;
// import com.alibaba.druid.support.http.ResourceServlet;
// import com.alibaba.druid.support.http.StatViewServlet;
// import com.alibaba.druid.support.http.WebStatFilter;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.boot.web.servlet.ServletRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.Map;

// /**
//  * @author zack <br>
//  * @create 2020-05-17 13:51 <br>
//  * @project seckill-backend <br>
//  */
// @Configuration
// public class DruidConfig {

//   @Bean
//   @ConfigurationProperties(prefix = "spring.datasource")
//   public DruidDataSource configDruid() {
//     return new DruidDataSource();
//   }

//   /** datasource management */
//   @Bean
//   public ServletRegistrationBean<StatViewServlet> configStatViewServlet() {
//     ServletRegistrationBean<StatViewServlet> statServletBean =
//         new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//     Map<String, String> initParams = new HashMap<>();
//     initParams.put(ResourceServlet.PARAM_NAME_USERNAME, "admin");
//     initParams.put(ResourceServlet.PARAM_NAME_PASSWORD, "admin");
//     initParams.put(ResourceServlet.PARAM_NAME_ALLOW, "");
//     initParams.put(ResourceServlet.PARAM_NAME_DENY, "192.168.43.143");
//     statServletBean.setInitParameters(initParams);

//     return statServletBean;
//   }

//   @Bean
//   public FilterRegistrationBean<WebStatFilter> configWebStatFilter() {
//     FilterRegistrationBean<WebStatFilter> webFilterBean = new FilterRegistrationBean<>();
//     webFilterBean.setFilter(new WebStatFilter());
//     Map<String, String> initParams = new HashMap<>();
//     initParams.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");

//     webFilterBean.setInitParameters(initParams);
//     webFilterBean.setUrlPatterns(Arrays.asList("/*"));

//     return webFilterBean;
//   }

// //   // TODO: this is also no work: https://www.cnblogs.com/lideqiang0909/p/11588729.html
// //   @Bean
// //   public FilterRegistrationBean filterRegistrationBean() {
// //       FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
// //       filterRegistrationBean.setFilter(new WebStatFilter());
// //       filterRegistrationBean.addUrlPatterns("/*");
// //       filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
// //       return filterRegistrationBean;
// //   }
// }
