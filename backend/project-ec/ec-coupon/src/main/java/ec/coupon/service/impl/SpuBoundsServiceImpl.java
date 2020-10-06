package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SpuBoundsEntity;
import ec.coupon.repository.SpuBoundsRepository;
import ec.coupon.service.SpuBoundsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsRepository, SpuBoundsEntity>
    implements SpuBoundsService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuBoundsEntity> page =
        this.page(
            new CommonQuery<SpuBoundsEntity>().getPage(params),
            new QueryWrapper<SpuBoundsEntity>());

    return new PageUtils(page);
  }
}
