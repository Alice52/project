package cn.edu.ntu.project.seckill.api.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zack <br/>
 * @create 2020-06-30 21:47 <br/>
 * @project seckill-backend <br/>
 */
@Configuration
public class MqConfig {

  public static final String SECKILL_QUEUE = "tutorial.project.sec-kill.queue";

  @Bean
  public Queue queue(){
    return new Queue(SECKILL_QUEUE,true);
  }
}
