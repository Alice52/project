package ec.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author zack <br>
 * @create 2020-10-05 15:29 <br>
 * @project project-ec <br>
 */
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }
}
