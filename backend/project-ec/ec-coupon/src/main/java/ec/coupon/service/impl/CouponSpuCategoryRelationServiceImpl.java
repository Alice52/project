package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponSpuCategoryRelationEntity;
import ec.coupon.repository.CouponSpuCategoryRelationRepository;
import ec.coupon.service.CouponSpuCategoryRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("couponSpuCategoryRelationService")
public class CouponSpuCategoryRelationServiceImpl
    extends ServiceImpl<CouponSpuCategoryRelationRepository, CouponSpuCategoryRelationEntity>
    implements CouponSpuCategoryRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponSpuCategoryRelationEntity> page =
        this.page(
            new CommonQuery<CouponSpuCategoryRelationEntity>().getPage(params),
            new QueryWrapper<CouponSpuCategoryRelationEntity>());

    return new PageUtils(page);
  }
}
