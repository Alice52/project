package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.HomeAdvEntity;
import ec.coupon.repository.HomeAdvRepository;
import ec.coupon.service.HomeAdvService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("homeAdvService")
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvRepository, HomeAdvEntity>
    implements HomeAdvService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<HomeAdvEntity> page =
        this.page(
            new CommonQuery<HomeAdvEntity>().getPage(params), new QueryWrapper<HomeAdvEntity>());

    return new PageUtils(page);
  }
}
