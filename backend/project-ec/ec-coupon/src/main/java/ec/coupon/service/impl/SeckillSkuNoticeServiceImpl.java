package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillSkuNoticeEntity;
import ec.coupon.repository.SeckillSkuNoticeRepository;
import ec.coupon.service.SeckillSkuNoticeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("seckillSkuNoticeService")
public class SeckillSkuNoticeServiceImpl
    extends ServiceImpl<SeckillSkuNoticeRepository, SeckillSkuNoticeEntity>
    implements SeckillSkuNoticeService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SeckillSkuNoticeEntity> page =
        this.page(
            new CommonQuery<SeckillSkuNoticeEntity>().getPage(params),
            new QueryWrapper<SeckillSkuNoticeEntity>());

    return new PageUtils(page);
  }
}
