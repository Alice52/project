package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SkuLadderEntity;
import ec.coupon.repository.SkuLadderRepository;
import ec.coupon.service.SkuLadderService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("skuLadderService")
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderRepository, SkuLadderEntity>
    implements SkuLadderService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SkuLadderEntity> page =
        this.page(
            new CommonQuery<SkuLadderEntity>().getPage(params),
            new QueryWrapper<SkuLadderEntity>());

    return new PageUtils(page);
  }
}
