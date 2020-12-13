package ec.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.SkuSaleAttrValueEntity;
import ec.product.repository.SkuSaleAttrValueRepository;
import ec.product.service.SkuSaleAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/** @author zack */
@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl
    extends ServiceImpl<SkuSaleAttrValueRepository, SkuSaleAttrValueEntity>
    implements SkuSaleAttrValueService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {

    IPage<SkuSaleAttrValueEntity> page =
        this.page(
            new CommonQuery<SkuSaleAttrValueEntity>().getPage(params),
            new QueryWrapper<SkuSaleAttrValueEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveSaleAttrValues(List<SkuSaleAttrValueEntity> saleAttrValueEntities) {

    if (!saleAttrValueEntities.isEmpty()) {
      this.saveBatch(saleAttrValueEntities);
    }
  }
}
