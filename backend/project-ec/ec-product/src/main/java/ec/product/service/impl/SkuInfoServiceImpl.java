package ec.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.SkuInfoEntity;
import ec.product.repository.SkuInfoRepository;
import ec.product.service.SkuInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/** @author zack */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoRepository, SkuInfoEntity>
    implements SkuInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuInfoEntity> page =
        this.page(
            new CommonQuery<SkuInfoEntity>().getPage(params), new QueryWrapper<SkuInfoEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {

    baseMapper.insert(skuInfoEntity);
  }

  @Override
  public PageUtils queryPageByCondition(Map<String, Object> params) {

    QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
    Object key = params.get("key");
    if (ObjectUtil.isNotEmpty(key)) {
      wrapper.and(w -> w.eq("sku_id", key).or().like("sku_name", key));
    }

    Object catelogId = params.get("catelogId");
    if (ObjectUtil.isNotEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId.toString())) {
      wrapper.eq("catalog_id", catelogId);
    }

    Object brandId = params.get("brandId");
    if (ObjectUtil.isNotEmpty(brandId) && !"0".equalsIgnoreCase(brandId.toString())) {
      wrapper.eq("brand_id", brandId);
    }

    Object min = params.get("min");
    if (ObjectUtil.isNotEmpty(min)) {
      wrapper.ge("price", min);
    }

    Object max = params.get("max");
    if (ObjectUtil.isNotEmpty(max) && !"0".equalsIgnoreCase(max.toString())) {
      wrapper.le("price", max);
    }

    IPage<SkuInfoEntity> page =
        this.page(new CommonQuery<SkuInfoEntity>().getPage(params), wrapper);

    return new PageUtils(page);
  }
}
