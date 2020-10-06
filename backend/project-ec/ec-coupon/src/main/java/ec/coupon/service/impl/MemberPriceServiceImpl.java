package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.MemberPriceEntity;
import ec.coupon.repository.MemberPriceRepository;
import ec.coupon.service.MemberPriceService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceRepository, MemberPriceEntity>
    implements MemberPriceService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberPriceEntity> page =
        this.page(
            new CommonQuery<MemberPriceEntity>().getPage(params),
            new QueryWrapper<MemberPriceEntity>());

    return new PageUtils(page);
  }
}
