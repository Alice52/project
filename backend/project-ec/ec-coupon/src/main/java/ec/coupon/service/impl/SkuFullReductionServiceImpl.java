package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SkuFullReductionEntity;
import ec.coupon.repository.SkuFullReductionRepository;
import ec.coupon.service.SkuFullReductionService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl
    extends ServiceImpl<SkuFullReductionRepository, SkuFullReductionEntity>
    implements SkuFullReductionService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuFullReductionEntity> page =
        this.page(
            new CommonQuery<SkuFullReductionEntity>().getPage(params),
            new QueryWrapper<SkuFullReductionEntity>());

    return new PageUtils(page);
  }
}
