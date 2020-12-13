package ec.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.product.repository.SkuImagesRepository;
import ec.product.entity.SkuImagesEntity;
import ec.product.service.SkuImagesService;

@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesRepository, SkuImagesEntity>
    implements SkuImagesService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuImagesEntity> page =
        this.page(
            new CommonQuery<SkuImagesEntity>().getPage(params),
            new QueryWrapper<SkuImagesEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveSkuImages(List<SkuImagesEntity> skuImagesEntities) {
    if (!skuImagesEntities.isEmpty()) {
      this.saveBatch(skuImagesEntities);
    }
  }
}
