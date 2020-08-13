package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.po.GoodsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-08-11 22:25 <br>
 * @project project-seckill <br>
 */
@Validated
@Mapper
public interface IGoodsRepository {

  /**
   * Create a new goods.
   *
   * @param po
   */
  void create(@Valid GoodsPO po);

  /**
   * Get goods detail by name.
   *
   * @param goodsName
   * @return GoodsPO
   */
  GoodsPO queryByName(String goodsName);

  /**
   * Get goods detail by id.
   *
   * @param goodsId
   * @return
   */
  @Deprecated
  GoodsPO queryById(@Param("id") String goodsId);

  /**
   * Full scale update goods.
   *
   * @param goodsId
   * @param po
   */
  void fullScaleUpdate(@Param("id") String goodsId, @Param("goods") GoodsPO po);

  /**
   * Update specify columns.
   *
   * @param goodsId
   * @param po
   */
  @Deprecated
  void update(@Param("id") String goodsId, @Param("goods") GoodsPO po);

  /**
   * Get by condition.
   *
   * @param goods
   * @return
   */
  GoodsPO getByCondition(GoodsBO goods);

  /**
   * * Get goods list.
   *
   * @param pageSize
   * @param currentPage
   * @param searchKey
   * @return
   */
  List<GoodsPO> list(
      @NotNull @Param("pageSize") Integer pageSize,
      @NotNull @Param("currentPage") Integer currentPage,
      @Param("searchKey") String searchKey);
}
