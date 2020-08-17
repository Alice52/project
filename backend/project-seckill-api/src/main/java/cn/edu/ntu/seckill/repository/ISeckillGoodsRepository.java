package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.bo.SeckillGoodsBO;
import cn.edu.ntu.seckill.model.po.SeckillGoodsPO;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020-08-17 21:40 <br>
 * @project project-seckill <br>
 */
@Mapper
public interface ISeckillGoodsRepository {

  /**
   * create a new seckill goods.
   *
   * @param seckillGoodsPO
   */
  void create(SeckillGoodsPO seckillGoodsPO);

  /**
   * Get seckill goods by goodsId.
   *
   * @param goodsId
   * @return
   */
  SeckillGoodsPO getByPK(@NotBlank String goodsId);

  /**
   * Get by condition.
   *
   * @param condition
   * @return
   */
  SeckillGoodsPO getByCondition(SeckillGoodsBO condition);
}
