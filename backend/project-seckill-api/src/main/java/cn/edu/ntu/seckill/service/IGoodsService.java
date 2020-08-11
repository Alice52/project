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
   * Get and convert to goodsVO from database.
   *
   * @param goodsId
   * @return
   */
  GoodsVO getById(@NotBlank String goodsId);

  /**
   * Update goods info from given goods.<br>
   * And the update must be full scale update.
   *
   * @param goodsBO
   * @return
   */
  String update(@Valid GoodsBO goodsBO);
}
