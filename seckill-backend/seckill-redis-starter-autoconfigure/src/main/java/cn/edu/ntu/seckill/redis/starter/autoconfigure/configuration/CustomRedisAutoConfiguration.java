package cn.edu.ntu.seckill.redis.starter.autoconfigure.configuration;

import cn.edu.ntu.seckill.redis.starter.autoconfigure.properties.CustomRedisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-05-17 17:46 <br>
 * @project seckill-backend <br>
 */
@Configuration
@ComponentScan(basePackages = {"cn.edu.ntu.seckill.redis.starter.autoconfigure"})
@EnableConfigurationProperties(CustomRedisProperties.class)
public class CustomRedisAutoConfiguration {

  @Resource CustomRedisProperties redisProperties;

  @Bean
  public JedisPool JedisPoolFactory() {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxIdle(redisProperties.getPoolMaxIdle());
    poolConfig.setMaxTotal(redisProperties.getPoolMaxTotal());
    poolConfig.setMaxWaitMillis(redisProperties.getPoolMaxWait() * 1000);
    JedisPool jp =
        new JedisPool(
            poolConfig,
            redisProperties.getHost(),
            redisProperties.getPort(),
            redisProperties.getTimeout() * 1000,
            redisProperties.getPassword(),
            0);
    return jp;
  }
}
