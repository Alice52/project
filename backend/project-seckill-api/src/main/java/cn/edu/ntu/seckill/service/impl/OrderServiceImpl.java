package cn.edu.ntu.seckill.service.impl;

import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.bo.OrderBO;
import cn.edu.ntu.seckill.model.bo.SeckillGoodsBO;
import cn.edu.ntu.seckill.repository.IGoodsOrderRepository;
import cn.edu.ntu.seckill.repository.ISeckillGoodsOrderRepository;
import cn.edu.ntu.seckill.service.*;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zack <br>
 * @create 2020-08-30 10:43 <br>
 * @project project-seckill <br>
 */
@Service
public class OrderServiceImpl implements IOrderService {

  @Resource private ISeckillGoodsService seckillGoodsService;
  @Resource private IGoodsService goodsService;
  @Resource private IGoodsOrderRepository goodsOrderRepository;
  @Resource private ISeckillGoodsOrderRepository seckillGoodsOrderRepository;

  @Resource private IStockLogService stockLogService;
  @Resource private IStockService stockService;

  @Override
  public OrderBO createOrder(
      String userId, String goodsId, String seckillGoodsId, Integer amount, String stockLogId) {

    if (StrUtil.isNotBlank(seckillGoodsId)) {
      boolean result = stockService.decreaseStockInCache(seckillGoodsId, amount);
      if (!result) {
        throw new BusinessException().new SeckillGoodsStock(seckillGoodsId);
      }
    }
    GoodsBO goodsBO = new GoodsBO();
    goodsBO.setId(goodsId);
    GoodsBO goods = goodsService.validateAndGetByConditionThenCache(goodsId, goodsBO);

    OrderBO order = new OrderBO();
    order.setId(generateOrderNo());
    order.setGoodsId(goodsId);
    order.setUserId(userId);
    order.setGoodsId(goodsId);
    order.setCount(amount);
    order.setPrice(goods.getPrice());
    order.setGoodsName(goods.getName());
    goodsOrderRepository.insertSelective(order);

    if (StrUtil.isNotBlank(seckillGoodsId)) {
      SeckillGoodsBO seckillGoods =
          seckillGoodsService.validateAndGetByConditionThenCache(
              seckillGoodsId, new SeckillGoodsBO(seckillGoodsId));
      order.setSeckillPrice(seckillGoods.getSeckillPrice());
      order.setSeckillGoodsId(seckillGoodsId);
      order.setOrderId(order.getId());
      seckillGoodsOrderRepository.insertSelective(order);
    }

    // set log data status to success.
    stockLogService.updateStatus(stockLogId, 2);

    return order;
  }

  /**
   * order id is 16 bit and follow this pattern: <br>
   * date[1-8] + random[9-14] + "00"
   *
   * @return
   */
  private static String generateOrderNo() {
    StringBuilder stringBuilder = new StringBuilder();
    LocalDateTime now = LocalDateTime.now();
    String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
    stringBuilder.append(nowDate);

    stringBuilder.append(IdUtil.fastSimpleUUID(), 0, 5);

    // 最后2位为分库分表位,暂时写死
    stringBuilder.append("00");

    return stringBuilder.toString();
  }
}
