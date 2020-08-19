package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.bo.SeckillGoodsBO;
import cn.edu.ntu.seckill.model.po.SeckillGoodsPO;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

  /**
   * Update seckill goods price.
   *
   * @param condition
   * @return
   */
  void updatePrice(@NotBlank @Param("goodsId") String goodsId, @Min(0) @NotNull @Param("price") BigDecimal price);
}
