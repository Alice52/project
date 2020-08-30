package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.po.SeckillStockPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-17 21:40 <br>
 * @project project-seckill <br>
 */
@Mapper
@Validated
public interface ISeckillStockRepository {

  /**
   * Creates stock with initial count.
   *
   * @param po
   */
  void create(@Valid SeckillStockPO po);

  /**
   * Get by goodsId.
   *
   * @param seckillGoodsId
   * @return
   */
  SeckillStockPO getBySeckillGoodsId(@NotBlank @Param("seckillGoodsId") String seckillGoodsId);

  /**
   * Get by id.
   *
   * @param id
   * @return
   */
  SeckillStockPO getByPK(@NotBlank @Param("id") String id);

  /**
   * Decrease stock by amount.
   * @param seckillGoodsId
   * @param stock
   * @return
   */

  int decrease(
      @NotBlank @Param("seckillGoodsId") String seckillGoodsId,
      @NotNull @Param("stock") Integer stock);

  /**
   * Update seckill goods stock.
   *
   * @param seckillGoodsId
   * @param stock
   * @return
   */
  int updateStock(
      @NotBlank @Param("seckillGoodsId") String seckillGoodsId,
      @NotNull @Param("stock") Integer stock);
}
