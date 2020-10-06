package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.entity.HomeSubjectEntity;
import ec.coupon.repository.HomeSubjectRepository;
import ec.coupon.service.HomeSubjectService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("homeSubjectService")
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectRepository, HomeSubjectEntity>
    implements HomeSubjectService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<HomeSubjectEntity> page =
        this.page(
            new CommonQuery<HomeSubjectEntity>().getPage(params),
            new QueryWrapper<HomeSubjectEntity>());

    return new PageUtils(page);
  }
}
