package cn.edu.ntu.seckill.mq;

import cn.edu.ntu.seckill.model.bo.StockLogBO;
import cn.edu.ntu.seckill.service.IOrderService;
import cn.edu.ntu.seckill.service.IStockLogService;
import cn.edu.ntu.seckill.service.IStockService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-08-30 12:50 <br>
 * @project project-seckill <br>
 */
@Slf4j
@Component
public class MqProducer {

  @Value("${apache.rocketmq.producer.producerGroup}")
  private String producerGroup;

  @Value("${apache.rocketmq.producer.transactionProducerGroup}")
  private String transactionProducerGroup;

  @Value("${apache.rocketmq.namesrvAddr}")
  private String nameAddr;

  @Value("${apache.rocketmq.topicName}")
  private String topicName;

  private DefaultMQProducer producer;
  private TransactionMQProducer transactionMQProducer;

  @Resource private IOrderService orderService;
  @Resource private IStockLogService stockLogService;

  @PostConstruct
  public void init() throws MQClientException {
    // mq producer initialization
    producer = new DefaultMQProducer(producerGroup);
    producer.setNamesrvAddr(nameAddr);
    producer.start();

    transactionMQProducer = new TransactionMQProducer(transactionProducerGroup);
    transactionMQProducer.setNamesrvAddr(nameAddr);
    transactionMQProducer.start();

    /** COMMIT_MESSAGE status, then message will be consumed. */
    transactionMQProducer.setTransactionListener(
        new TransactionListener() {
          @Override
          public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            // create order
            String goodsId = (String) ((Map) arg).get("goodsId");
            String seckillGoodsId = (String) ((Map) arg).get("seckillGoodsId");
            String userId = (String) ((Map) arg).get("userId");
            Integer amount = (Integer) ((Map) arg).get("amount");
            String stockLogId = (String) ((Map) arg).get("stockLogId");
            try {
              orderService.createOrder(userId, goodsId, seckillGoodsId, amount, stockLogId);
            } catch (Exception e) {
              log.error("create order error", e);
              stockLogService.updateStatus(stockLogId, 3);
              return LocalTransactionState.ROLLBACK_MESSAGE;
            }

            return LocalTransactionState.COMMIT_MESSAGE;
          }

          @Override
          public LocalTransactionState checkLocalTransaction(MessageExt msg) {
            // judge transaction status according to stock log data: COMMIT/ROLLBACK/UNKNOWN
            String jsonString = new String(msg.getBody());
            Map<String, Object> map = BeanUtil.beanToMap(jsonString);
            String stockLogId = (String) map.get("stockLogId");
            StockLogBO stockLog = stockLogService.getByPK(stockLogId);
            if (stockLog == null) {
              return LocalTransactionState.UNKNOW;
            }
            if (stockLog.getStatus().intValue() == 2) {
              return LocalTransactionState.COMMIT_MESSAGE;
            } else if (stockLog.getStatus().intValue() == 1) {
              return LocalTransactionState.UNKNOW;
            }

            return LocalTransactionState.ROLLBACK_MESSAGE;
          }
        });
  }

  /**
   * 事务型同步库存扣减消息
   *
   * @param userId
   * @param goodsId
   * @param seckillGoodsId
   * @param amount
   * @param stockLogId
   * @return
   */
  public boolean transactionAsyncReduceStock(
      String userId, String goodsId, String seckillGoodsId, Integer amount, String stockLogId) {
    Map<String, Object> bodyMap = new HashMap<>(4);
    bodyMap.put("goodsId", goodsId);
    bodyMap.put("seckillGoodsId", seckillGoodsId);
    bodyMap.put("amount", amount);

    Map<String, Object> argsMap = new HashMap<>(8);
    argsMap.put("goodsId", goodsId);
    argsMap.put("amount", amount);
    argsMap.put("userId", userId);
    argsMap.put("seckillGoodsId", seckillGoodsId);
    argsMap.put("stockLogId", stockLogId);

    Message message =
        new Message(
            topicName, "increase", JSONUtil.toJsonStr(bodyMap).getBytes(Charset.forName("UTF-8")));
    TransactionSendResult sendResult = null;
    try {
      // sendMessageInTransaction will send message and set status as `prepared`,
      // and execute executeLocalTransaction method
      sendResult = transactionMQProducer.sendMessageInTransaction(message, argsMap);
    } catch (MQClientException e) {
      log.error("send create order message error", e);
      return false;
    }

    if (sendResult.getLocalTransactionState() == LocalTransactionState.COMMIT_MESSAGE) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 同步库存扣减消息
   *
   * @param goodsId
   * @param seckillGoodsId
   * @param amount
   * @return
   */
  @Deprecated
  public boolean asyncReduceStock(String goodsId, String seckillGoodsId, Integer amount) {
    Map<String, Object> bodyMap = new HashMap<>();
    bodyMap.put("goodsId", goodsId);
    bodyMap.put("seckillGoodsId", seckillGoodsId);
    bodyMap.put("amount", amount);

    Message message =
        new Message(
            topicName, "increase", JSONUtil.toJsonStr(bodyMap).getBytes(Charset.forName("UTF-8")));
    try {
      producer.send(message);
    } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
      log.error("send reduce stock message error: ", e);
      return false;
    }

    return true;
  }
}
