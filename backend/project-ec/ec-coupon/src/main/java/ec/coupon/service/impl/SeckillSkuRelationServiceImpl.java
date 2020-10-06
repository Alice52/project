package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillSkuRelationEntity;
import ec.coupon.repository.SeckillSkuRelationRepository;
import ec.coupon.service.SeckillSkuRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("seckillSkuRelationService")
public class SeckillSkuRelationServiceImpl
    extends ServiceImpl<SeckillSkuRelationRepository, SeckillSkuRelationEntity>
    implements SeckillSkuRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SeckillSkuRelationEntity> page =
        this.page(
            new CommonQuery<SeckillSkuRelationEntity>().getPage(params),
            new QueryWrapper<SeckillSkuRelationEntity>());

    return new PageUtils(page);
  }
}
