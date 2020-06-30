package cn.edu.ntu.project.seckill.api.repository;

import cn.edu.ntu.project.seckill.api.entities.SeckillGoods;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-01 23:27 <br>
 * @project seckill-backend <br>
 */
@Mapper
public interface IGoodsRepository {

  /**
   * List detail of goods.
   *
   * @return List<GoodsVo>
   */
  List<GoodsVo> list();

  /**
   * Get GoodsVo detail info.
   *
   * @param gid
   * @return GoodsVo
   */
  @Select(
      "SELECT g.id, g.goods_name, g.goods_title, g.goods_img, g.goods_detail, g.goods_price, g.goods_stock,"
          + " sg.seckill_price, sg.stock_count, sg.start_date, sg.end_date"
          + " FROM `seckill.seckill_goods` sg"
          + " LEFT JOIN `seckill.goods` g"
          + " ON sg.goods_id = g.id"
          + " WHERE g.id = #{gid}")
  GoodsVo detail(@Param("gid") String gid);

  /**
   * Reset goods number.
   *
   * @param goods
   */
  @Update(
      "UPDATE `seckill.seckill_goods` SET stock_count = #{stockCount} WHERE goods_id = #{goodsId}")
  void reset(SeckillGoods goods);

  /**
   * Reduce goods stock.
   *
   * @param g
   * @return
   */
  @Update(
      "UPDATE `seckill.seckill_goods` SET stock_count = stock_count - 1 WHERE goods_id = #{goodsId} AND stock_count > 0")
  int reduceStock(SeckillGoods g);
}
