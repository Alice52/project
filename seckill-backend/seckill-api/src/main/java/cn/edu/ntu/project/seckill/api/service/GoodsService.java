package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.entities.Goods;
import cn.edu.ntu.project.seckill.api.entities.SeckillGoods;
import cn.edu.ntu.project.seckill.api.repository.IGoodsRepository;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-01 23:26 <br>
 * @project seckill-backend <br>
 */
@Service
public class GoodsService implements IGoodsService {

  @Resource IGoodsRepository goodsRepository;

  @Override
  public List<GoodsVo> list() {
    return goodsRepository.list();
  }

  @Override
  public GoodsVo detail(String gid) {

    // validate gid
    return goodsRepository.detail(gid);
  }

  @Override
  public void resetStock(List<GoodsVo> goodsList) {

    for (GoodsVo goods : goodsList) {
      SeckillGoods g = new SeckillGoods();
      g.setGoodsId(goods.getId());
      g.setStockCount(goods.getStockCount());
      goodsRepository.reset(g);
    }
  }

  @Override
  public boolean reduceStock(GoodsVo goods) {
    SeckillGoods g = new SeckillGoods();
    g.setGoodsId(goods.getId());
    int ret = goodsRepository.reduceStock(g);
    return ret > 0;
  }
}
