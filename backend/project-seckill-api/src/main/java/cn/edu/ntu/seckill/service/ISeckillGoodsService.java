package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.model.bo.SeckillGoodsBO;
import cn.edu.ntu.seckill.model.vo.ListVO;
import cn.edu.ntu.seckill.model.vo.SeckillGoodsVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-17 21:30 <br>
 * @project project-seckill <br>
 */
@Validated
public interface ISeckillGoodsService {
  /**
   * add seckill goods.
   *
   * @param seckillGoodsBO
   * @return boolean
   */
  String publishPromo(@Validated SeckillGoodsBO seckillGoodsBO);

  /**
   * View seckill goods details by goodsId.
   *
   * @param goodsId
   * @return
   */
  SeckillGoodsVO view(@NotBlank String goodsId);

  /**
   * Lists all seckill goods.
   *
   * @param pageSize
   * @param currentPage
   * @param searchKey
   * @return
   */
  ListVO<SeckillGoodsVO> list(
      @NotNull Integer pageSize, @NotNull Integer currentPage, String searchKey);
}
