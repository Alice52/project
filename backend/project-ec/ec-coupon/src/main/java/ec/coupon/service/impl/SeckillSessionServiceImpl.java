package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillSessionEntity;
import ec.coupon.repository.SeckillSessionRepository;
import ec.coupon.service.SeckillSessionService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("seckillSessionService")
public class SeckillSessionServiceImpl
    extends ServiceImpl<SeckillSessionRepository, SeckillSessionEntity>
    implements SeckillSessionService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SeckillSessionEntity> page =
        this.page(
            new CommonQuery<SeckillSessionEntity>().getPage(params),
            new QueryWrapper<SeckillSessionEntity>());

    return new PageUtils(page);
  }
}
