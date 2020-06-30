package cn.edu.ntu.project.seckill.api.mq;

import cn.edu.ntu.project.seckill.api.configuration.MqConfig;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.utils.SecKillBeanUtils;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-06-30 22:18 <br>
 * @project seckill-backend <br>
 */
@Component
@Slf4j
public class MqSender {

  @Resource AmqpTemplate amqpTemplate;

  public void sendSecKillMessage(SecKillMessage sm) {
    String msg = JSONUtil.toJsonStr(sm);
    log.info("send message:" + msg);
    amqpTemplate.convertAndSend(MqConfig.SECKILL_QUEUE, msg);
  }
}
