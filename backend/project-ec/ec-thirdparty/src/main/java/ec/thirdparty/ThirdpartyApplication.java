package ec.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author zack <br>
 * @create 2020-09-30 23:27 <br>
 * @project project-ec <br>
 */
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class ThirdpartyApplication {
  public static void main(String[] args) {
    SpringApplication.run(ThirdpartyApplication.class, args);
  }
}
