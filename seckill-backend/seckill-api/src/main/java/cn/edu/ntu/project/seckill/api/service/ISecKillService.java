package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.entities.SecKillOrder;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;

import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-30 21:03 <br>
 * @project seckill-backend <br>
 */
public interface ISecKillService {

  /**
   * Reset goods.
   *
   * @param goodsList
   */
  void reset(List<GoodsVo> goodsList);

    SecKillOrder secKill(SeckillUser user, GoodsVo goods);

    String getSecKillResult(String userId, String goodsId);
}
