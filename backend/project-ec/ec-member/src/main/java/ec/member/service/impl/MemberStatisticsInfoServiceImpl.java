package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberStatisticsInfoEntity;
import ec.member.repository.MemberStatisticsInfoRepository;
import ec.member.service.MemberStatisticsInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberStatisticsInfoService")
public class MemberStatisticsInfoServiceImpl
    extends ServiceImpl<MemberStatisticsInfoRepository, MemberStatisticsInfoEntity>
    implements MemberStatisticsInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberStatisticsInfoEntity> page =
        this.page(
            new CommonQuery<MemberStatisticsInfoEntity>().getPage(params),
            new QueryWrapper<MemberStatisticsInfoEntity>());

    return new PageUtils(page);
  }
}
