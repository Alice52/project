package ec.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zack <br>
 * @create 2020-09-30 23:19 <br>
 * @project project-ec <br>
 */
@EnableTransactionManagement
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class WareApplication {
  public static void main(String[] args) {
    SpringApplication.run(WareApplication.class, args);
  }
}
