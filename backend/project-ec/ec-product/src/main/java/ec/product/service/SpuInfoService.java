package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.SpuInfoEntity;
import ec.product.model.vo.SpuSaveVO;

import java.util.Map;

/**
 * spu信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Save Spu info, including spu, images, sku, price, etc
   *
   * @param vo
   */
  void saveSpuInfo(SpuSaveVO vo);

  /**
   * Save base spu info.
   *
   * @param entity
   */
  void saveSpuBaseInfo(SpuInfoEntity entity);

  /**
   * Get all spu info, which is satisfied.
   *
   * @param params
   * @return
   */
  PageUtils queryPageByCondition(Map<String, Object> params);

  /**
   * Spu up.
   *
   * @param spuId
   */
  void spuUp(Long spuId);

  /**
   * Spu down.
   *
   * @param spuId
   */
  void spuDown(Long spuId);
}
