package cn.edu.ntu.project.seckill.api.mq;

import cn.edu.ntu.project.seckill.api.configuration.MqConfig;
import cn.edu.ntu.project.seckill.api.entities.SecKillOrder;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.service.GoodsService;
import cn.edu.ntu.project.seckill.api.service.OrderService;
import cn.edu.ntu.project.seckill.api.service.SecKillService;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.utils.SecKillBeanUtils;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-06-30 21:59 <br>
 * @project seckill-backend <br>
 */
@Slf4j
@Component
public class MqReceiver {

  @Resource RedisService redisService;

  @Resource GoodsService goodsService;

  @Resource OrderService orderService;

  @Resource SecKillService secKillService;

  @RabbitListener(queues = MqConfig.SECKILL_QUEUE)
  public void receive(String message) {
    log.info("receive message:" + message);
    SecKillMessage sm = JSONUtil.toBean(message, SecKillMessage.class);
    SeckillUser user = sm.getUser();
    String goodsId = sm.getGoodsId();

    GoodsVo goods = goodsService.detail(goodsId);
    int stock = goods.getStockCount();
    if (stock <= 0) {
      return;
    }
    // 判断是否已经秒杀到了
    SecKillOrder order = orderService.getSecKillOrderByUserIdGoodsId(user.getNickname(), goodsId);
    if (order != null) {
      return;
    }
    // 减库存 下订单 写入秒杀订单
    secKillService.secKill(user, goods);
  }
}
