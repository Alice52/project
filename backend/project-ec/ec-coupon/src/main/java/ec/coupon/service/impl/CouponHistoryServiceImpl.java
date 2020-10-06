package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponHistoryEntity;
import ec.coupon.repository.CouponHistoryRepository;
import ec.coupon.service.CouponHistoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("couponHistoryService")
public class CouponHistoryServiceImpl
    extends ServiceImpl<CouponHistoryRepository, CouponHistoryEntity>
    implements CouponHistoryService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CouponHistoryEntity> page =
        this.page(
            new CommonQuery<CouponHistoryEntity>().getPage(params),
            new QueryWrapper<CouponHistoryEntity>());

    return new PageUtils(page);
  }
}
