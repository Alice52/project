package cn.edu.ntu.seckill.mq;

import cn.edu.ntu.seckill.service.IStockService;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-08-30 14:00 <br>
 * @project project-seckill <br>
 */
@Component
public class MqConsumer {

  private DefaultMQPushConsumer consumer;

  @Value("${apache.rocketmq.consumer.consumerGroup}")
  private String consumerGroup;

  @Value("${apache.rocketmq.namesrvAddr}")
  private String nameAddr;

  @Value("${apache.rocketmq.topicName}")
  private String topicName;

  @Resource private IStockService stockService;

  @PostConstruct
  public void init() throws MQClientException {
    consumer = new DefaultMQPushConsumer(consumerGroup);
    consumer.setNamesrvAddr(nameAddr);
    consumer.subscribe(topicName, "*");

    /** reduce stock in mysql. */
    consumer.registerMessageListener(
        (List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
          Message msg = msgs.get(0);
          String jsonString = new String(msg.getBody());
          Map<String, Object> map = JSON.parseObject(jsonString, Map.class);
          String goodsId = (String) map.get("goodsId");
          String seckillGoodsId = (String) map.get("seckillGoodsId");
          Integer amount = (Integer) map.get("amount");

          stockService.decreaseStock(goodsId, seckillGoodsId, amount);
          return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

    consumer.start();
  }
}
