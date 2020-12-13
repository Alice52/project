package ec.common.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author zack <br>
 * @create 2020-04-02 21:27 <br>
 */
public interface IFeignLogConfiguration {

  /**
   * Feign log level.
   *
   * @return
   */
  @Bean
  default Logger.Level feignLogLevel() {
    return Logger.Level.FULL;
  }
}
