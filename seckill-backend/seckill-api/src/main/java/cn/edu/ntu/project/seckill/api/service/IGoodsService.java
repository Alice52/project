package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-29 20:14 <br>
 * @project seckill-backend <br>
 */
public interface IGoodsService {

  /**
   * list of goods.
   *
   * @return List<GoodsVo>
   */
  List<GoodsVo> list();

  /**
   * Get GoodsVo list detail info.
   *
   * @param gid
   * @return GoodsVo
   */
  GoodsVo detail(String gid);

  /**
   * Reset goods stock.
   *
   * @param goodsList
   */
  void resetStock(List<GoodsVo> goodsList);

  /**
   * Reduce goods stock.
   *
   * @param goods
   * @return
   */
  boolean reduceStock(GoodsVo goods);
}
