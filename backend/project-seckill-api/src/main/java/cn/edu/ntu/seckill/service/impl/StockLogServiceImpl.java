package cn.edu.ntu.seckill.service.impl;

import cn.edu.ntu.seckill.converter.StockLogConverter;
import cn.edu.ntu.seckill.model.bo.StockLogBO;
import cn.edu.ntu.seckill.model.po.StockLogPO;
import cn.edu.ntu.seckill.repository.IStockLogRepository;
import cn.edu.ntu.seckill.service.IStockLogService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-08-30 12:39 <br>
 * @project project-seckill <br>
 */
@Service
public class StockLogServiceImpl implements IStockLogService {

  @Resource private IStockLogRepository stockLogRepository;

  @Override
  public String initStockLog(String goodsId, String seckillGoodsId, Integer amount) {

    StockLogBO stockLog = new StockLogBO();
    stockLog.setGoodsId(goodsId);
    stockLog.setAmount(amount);
    stockLog.setStatus(1);

    if (StrUtil.isNotBlank(seckillGoodsId)) {
      stockLog.setSeckillGoodsId(seckillGoodsId);
    }

    StockLogPO po = StockLogConverter.INSTANCE.bo2po(stockLog);
    stockLogRepository.create(po);

    return po.getId();
  }

  @Override
  public void updateStatus(String stockLogId, Integer status) {
    stockLogRepository.updateStatus(stockLogId, status);
  }

  @Override
  public StockLogBO getByPK(String id) {

    StockLogPO stockLogPO = stockLogRepository.getByPK(id);

    return StockLogConverter.INSTANCE.po2bo(stockLogPO);
  }
}
