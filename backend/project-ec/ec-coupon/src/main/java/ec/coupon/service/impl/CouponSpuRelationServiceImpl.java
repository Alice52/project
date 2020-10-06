package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponSpuRelationEntity;
import ec.coupon.repository.CouponSpuRelationRepository;
import ec.coupon.service.CouponSpuRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("couponSpuRelationService")
public class CouponSpuRelationServiceImpl
    extends ServiceImpl<CouponSpuRelationRepository, CouponSpuRelationEntity>
    implements CouponSpuRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponSpuRelationEntity> page =
        this.page(
            new CommonQuery<CouponSpuRelationEntity>().getPage(params),
            new QueryWrapper<CouponSpuRelationEntity>());

    return new PageUtils(page);
  }
}
