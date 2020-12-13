package ec.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.CategoryBrandRelationEntity;
import ec.product.entity.CategoryEntity;
import ec.product.model.vo.CategoryEntityVO;
import ec.product.repository.CategoryRepository;
import ec.product.service.CategoryBrandRelationService;
import ec.product.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static ec.product.converter.CategoryEntityConverter.INSTANCE;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryRepository, CategoryEntity>
    implements CategoryService {

  @Resource private CategoryBrandRelationService categoryBrandRelationService;

  @Override
  public boolean updateById(CategoryEntity entity) {

    baseMapper.updateById(entity);

    UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("catelog_id", entity.getCatId()).set("catelog_name", entity.getName());

    return categoryBrandRelationService.update(updateWrapper);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CategoryEntity> page =
        this.page(new CommonQuery<CategoryEntity>().getPage(params), new QueryWrapper<>());

    return new PageUtils(page);
  }

  @Override
  public List<CategoryEntityVO> listWithTree() {
    final List<CategoryEntityVO> categoryEntityVOS = INSTANCE.pos2vos(baseMapper.selectList(null));

    return categoryEntityVOS.stream()
        .filter(x -> x.getCatLevel() == 1)
        .map(
            entity -> {
              entity.setChildren(getChildren(entity, categoryEntityVOS));
              return entity;
            })
        .sorted(Comparator.comparingInt(CategoryEntityVO::getSort))
        .collect(Collectors.toList());
  }

  @Override
  public void removeMenuByIds(List<Long> catIds) {

    // TODO: if this menu is using, we cannot delete.
    baseMapper.deleteBatchIds(catIds);
  }

  @Override
  public List<Long> findCatelogPath(Long catelogId) {
    List<Long> paths = new ArrayList<>();
    if (catelogId == 0) {
      return paths;
    }

    List<CategoryEntity> entityList = baseMapper.selectList(null);
    List<Long> path = findParentPath(catelogId, entityList, paths);
    Collections.reverse(path);

    return path;
  }

  private List<Long> findParentPath(
      Long catelogId, List<CategoryEntity> entityList, List<Long> paths) {

    paths.add(catelogId);
    Long parentCid =
        entityList.parallelStream()
            .filter(x -> x.getCatId().equals(catelogId))
            .map(x -> x.getParentCid())
            .findFirst()
            .get();

    if (parentCid != 0) {
      findParentPath(parentCid, entityList, paths);
    }

    return paths;
  }

  private List<CategoryEntityVO> getChildren(
      CategoryEntityVO entity, List<CategoryEntityVO> allEntities) {

    return allEntities.stream()
        .filter(x -> x.getParentCid().equals(entity.getCatId()))
        .map(
            e -> {
              e.setChildren(getChildren(e, allEntities));
              return e;
            })
        .sorted(Comparator.comparingInt(CategoryEntityVO::getSort))
        .collect(Collectors.toList());
  }
}
