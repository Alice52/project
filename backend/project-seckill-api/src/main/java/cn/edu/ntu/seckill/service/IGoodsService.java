package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.vo.GoodsVO;
import cn.edu.ntu.seckill.model.vo.ListVO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

  /**
   * Show goods list on page
   *
   * @param pageSize
   * @param currentPage
   * @param searchKey
   * @return
   */
  ListVO<GoodsVO> list(@NotNull Integer pageSize, @NotNull Integer currentPage, String searchKey);

  /**
   * Get BO.
   *
   * @param goodsId
   * @param bo
   * @return
   */
  GoodsBO validateAndGetByConditionThenCache(@NotBlank String goodsId, GoodsBO bo);

  /**
   * decrease goods stock.
   *
   * @param goodsId
   * @param amount
   * @return
   * @throws BusinessException
   */
  @Transactional(
      propagation = Propagation.REQUIRED,
      isolation = Isolation.READ_COMMITTED,
      rollbackFor = {Exception.class})
  boolean decreaseStock(@NotBlank String goodsId, @Min(1) Integer amount) throws BusinessException;
}
