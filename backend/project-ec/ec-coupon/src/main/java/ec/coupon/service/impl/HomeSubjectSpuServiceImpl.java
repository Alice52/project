package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.HomeSubjectSpuEntity;
import ec.coupon.repository.HomeSubjectSpuRepository;
import ec.coupon.service.HomeSubjectSpuService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl
    extends ServiceImpl<HomeSubjectSpuRepository, HomeSubjectSpuEntity>
    implements HomeSubjectSpuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<HomeSubjectSpuEntity> page =
        this.page(
            new CommonQuery<HomeSubjectSpuEntity>().getPage(params),
            new QueryWrapper<HomeSubjectSpuEntity>());

    return new PageUtils(page);
  }
}
