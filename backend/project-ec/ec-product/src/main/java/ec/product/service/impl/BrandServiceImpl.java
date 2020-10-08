package ec.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.BrandEntity;
import ec.product.entity.CategoryBrandRelationEntity;
import ec.product.repository.BrandRepository;
import ec.product.service.BrandService;
import ec.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandRepository, BrandEntity>
    implements BrandService {

  @Resource private CategoryBrandRelationService categoryBrandRelationService;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {

    QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();

    String key = (String) params.get("key");
    if (StrUtil.isNotBlank(key)) {
      wrapper.like("descript", key).or().like("name", key);
    }

    IPage<BrandEntity> page = this.page(new CommonQuery<BrandEntity>().getPage(params), wrapper);

    return new PageUtils(page);
  }

  @Override
  public boolean updateById(BrandEntity entity) {

    baseMapper.updateById(entity);
    // update category-brand-relation table
    UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("brand_id", entity.getBrandId()).set("brand_name", entity.getName());

    return categoryBrandRelationService.update(updateWrapper);
  }
}
