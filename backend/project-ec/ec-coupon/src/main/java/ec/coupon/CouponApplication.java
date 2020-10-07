package ec.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * exclude datasource @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 *
 * @author zack
 * @create 2020-09-30 23:16 <br>
 * @project project-ec <br>
 */
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class CouponApplication {
  public static void main(String[] args) {
    SpringApplication.run(CouponApplication.class, args);
  }
}
