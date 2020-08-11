package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.po.GoodsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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
  GoodsPO queryById(@Param("id") String goodsId);

  /**
   * Full scale update goods.
   *
   * @param goodsId
   * @param po
   */
  void update(@Param("id") String goodsId, @Param("goods") GoodsPO po);
}
