package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.SkuSaleAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Save sku sale attr values.
   *
   * @param saleAttrValueEntities
   */
  void saveSaleAttrValues(List<SkuSaleAttrValueEntity> saleAttrValueEntities);
}
