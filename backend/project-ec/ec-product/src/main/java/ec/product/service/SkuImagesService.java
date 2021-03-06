package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.SkuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Save sku images.
   *
   * @param skuImagesEntities
   */
  void saveSkuImages(List<SkuImagesEntity> skuImagesEntities);
}
