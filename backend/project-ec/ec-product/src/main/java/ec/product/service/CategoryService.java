package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.CategoryEntity;
import ec.product.model.vo.CategoryEntityVO;

import java.util.List;
import java.util.Map;

/**
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:35
 */
public interface CategoryService extends IService<CategoryEntity> {

  /**
   * Query category by params.
   *
   * @param params
   * @return
   */
  PageUtils queryPage(Map<String, Object> params);

  /**
   * Get all categories, and organized by tree.
   *
   * @return
   */
  List<CategoryEntityVO> listWithTree();

  /**
   * Remove menu by ids.
   *
   * @param catIds
   */
  void removeMenuByIds(List<Long> catIds);

  /**
   * Find full path of specified catelog
   *
   * @param catelogId
   * @return
   */
  List<Long> findCatelogPath(Long catelogId);

  @Override
  boolean updateById(CategoryEntity entity);
}
