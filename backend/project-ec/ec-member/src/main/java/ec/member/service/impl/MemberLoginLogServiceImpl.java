package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberLoginLogEntity;
import ec.member.repository.MemberLoginLogRepository;
import ec.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl
    extends ServiceImpl<MemberLoginLogRepository, MemberLoginLogEntity>
    implements MemberLoginLogService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberLoginLogEntity> page =
        this.page(
            new CommonQuery<MemberLoginLogEntity>().getPage(params),
            new QueryWrapper<MemberLoginLogEntity>());

    return new PageUtils(page);
  }
}
