package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Save spu description info.
   *
   * @param spuInfoDescEntity
   */
  void saveSpuInfoDesc(SpuInfoDescEntity spuInfoDescEntity);
}
