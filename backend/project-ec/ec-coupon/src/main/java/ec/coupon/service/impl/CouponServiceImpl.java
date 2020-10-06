package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponEntity;
import ec.coupon.repository.CouponRepository;
import ec.coupon.service.CouponService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponRepository, CouponEntity>
    implements CouponService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponEntity> page =
        this.page(
            new CommonQuery<CouponEntity>().getPage(params), new QueryWrapper<CouponEntity>());

    return new PageUtils(page);
  }
}
