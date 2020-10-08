package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.CategoryBrandRelationEntity;
import ec.product.model.CategoryBrandRelationVO;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<CategoryBrandRelationVO> getbyBrandId(Long brandId);

  @Override
  boolean save(CategoryBrandRelationEntity entity);
}
