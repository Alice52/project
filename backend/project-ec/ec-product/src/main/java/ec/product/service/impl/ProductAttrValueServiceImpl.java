package ec.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.product.repository.ProductAttrValueRepository;
import ec.product.entity.ProductAttrValueEntity;
import ec.product.service.ProductAttrValueService;

@Service("productAttrValueService")
public class ProductAttrValueServiceImpl
    extends ServiceImpl<ProductAttrValueRepository, ProductAttrValueEntity>
    implements ProductAttrValueService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<ProductAttrValueEntity> page =
        this.page(
            new CommonQuery<ProductAttrValueEntity>().getPage(params),
            new QueryWrapper<ProductAttrValueEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveAttrValues(List<ProductAttrValueEntity> attrValueEntities) {
    if (ObjectUtil.isNotNull(attrValueEntities) && !attrValueEntities.isEmpty()) {
      this.saveBatch(attrValueEntities);
    }
  }
}
