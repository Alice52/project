package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.configuration.RedisOrderKeyEnum;
import cn.edu.ntu.project.seckill.api.entities.Order;
import cn.edu.ntu.project.seckill.api.entities.SecKillOrder;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.repository.IOrderRepository;
import cn.edu.ntu.project.seckill.api.repository.ISecKillOrderRepository;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-06-30 21:12 <br>
 * @project seckill-backend <br>
 */
@Service
public class OrderService implements IOrderService {

  @Resource ISecKillOrderRepository secKillOrderRepository;
  @Resource IOrderRepository orderRepository;
  @Resource RedisService redisService;

  @Override
  public void deleteOrders() {
    secKillOrderRepository.deleteOrders();
    orderRepository.deleteOrders();
  }

  @Override
  public SecKillOrder getSecKillOrderByUserIdGoodsId(String username, String goodsId) {

    return secKillOrderRepository.getByCondition(username, goodsId);
  }

  @Override
  @Transactional
  public SecKillOrder createOrder(SeckillUser user, GoodsVo goods) {
    Order orderInfo = new Order();
    orderInfo.setCreateAt(LocalDateTime.now());
    orderInfo.setDeliveryAddrId(IdUtil.simpleUUID());
    orderInfo.setGoodsCount(1);
    orderInfo.setGoodsId(goods.getId());
    orderInfo.setGoodsName(goods.getGoodsName());
    orderInfo.setGoodsPrice(goods.getSeckillPrice());
    orderInfo.setOrderChannel(1);
    orderInfo.setOrderStatus(0);
    orderInfo.setUserId(user.getNickname());
    orderInfo.setId(IdUtil.fastUUID());
    orderRepository.insert(orderInfo);
    SecKillOrder seckillOrder = new SecKillOrder();
    seckillOrder.setGoodsId(goods.getId());
    seckillOrder.setOrderId(orderInfo.getId());
    seckillOrder.setUserId(user.getNickname());
    seckillOrder.setId(IdUtil.fastUUID());
    secKillOrderRepository.insertSecKillOrder(seckillOrder);

    redisService.set(
        RedisOrderKeyEnum.ORDER_USER, "" + user.getNickname() + "_" + goods.getId(), seckillOrder);

    return seckillOrder;
  }
}
