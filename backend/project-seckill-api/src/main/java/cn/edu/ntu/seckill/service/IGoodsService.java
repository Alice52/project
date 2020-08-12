package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.vo.GoodsVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020-08-11 22:18 <br>
 * @project project-seckill <br>
 */
@Validated
public interface IGoodsService {

  /**
   * Create new goods.
   *
   * @param goodsBO
   * @return
   */
  String create(@Valid GoodsBO goodsBO);

  /**
   * Get and convert to goodsVO from database by goods id.
   *
   * @param goodsId
   * @return
   */
  GoodsVO getById(@NotBlank String goodsId);

  /**
   * Get and convert to goodsVO from database by goods name.
   *
   * @param name
   * @return
   */
  GoodsVO getByName(@NotBlank String name);

  /**
   * And also can full scale update.
   *
   * @param goodsBO
   * @param isFullScaleUpdate
   * @return
   */
  String fullScaleUpdate(@Valid GoodsBO goodsBO, boolean isFullScaleUpdate);
}