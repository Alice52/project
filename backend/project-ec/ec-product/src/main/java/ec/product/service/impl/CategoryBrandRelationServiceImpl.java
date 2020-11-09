package ec.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.BrandEntity;
import ec.product.entity.CategoryBrandRelationEntity;
import ec.product.entity.CategoryEntity;
import ec.product.model.vo.CategoryBrandRelationVO;
import ec.product.repository.CategoryBrandRelationRepository;
import ec.product.service.BrandService;
import ec.product.service.CategoryBrandRelationService;
import ec.product.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static ec.product.converter.CategoryBrandRelationConverter.INSTANCE;

@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl
    extends ServiceImpl<CategoryBrandRelationRepository, CategoryBrandRelationEntity>
    implements CategoryBrandRelationService {

  @Resource private BrandService brandService;
  @Resource private CategoryService categoryService;

  @Override
  public boolean save(CategoryBrandRelationEntity entity) {

    BrandEntity brand = brandService.getById(entity.getBrandId());
    entity.setBrandName(brand.getName());

    CategoryEntity category = categoryService.getById(entity.getCatelogId());
    entity.setCatelogName(category.getName());

    final int insert = baseMapper.insert(entity);

    return insert > 0;
  }

  @Override
  public List<CategoryBrandRelationVO> getbyBrandId(Long brandId) {
    QueryWrapper<CategoryBrandRelationEntity> wrapper =
        new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId);

    List<CategoryBrandRelationVO> list = INSTANCE.pos2vos(baseMapper.selectList(wrapper));

    return list;
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CategoryBrandRelationEntity> page =
        this.page(
            new CommonQuery<CategoryBrandRelationEntity>().getPage(params),
            new QueryWrapper<CategoryBrandRelationEntity>());

    return new PageUtils(page);
  }
}
