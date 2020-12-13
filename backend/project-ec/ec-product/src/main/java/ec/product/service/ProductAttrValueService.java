package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Save spu attr value info.
   *
   * @param attrValueEntities
   */
  void saveAttrValues(List<ProductAttrValueEntity> attrValueEntities);
}
