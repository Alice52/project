package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberLevelEntity;
import ec.member.repository.MemberLevelRepository;
import ec.member.service.MemberLevelService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelRepository, MemberLevelEntity>
    implements MemberLevelService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberLevelEntity> page =
        this.page(
            new CommonQuery<MemberLevelEntity>().getPage(params),
            new QueryWrapper<MemberLevelEntity>());

    return new PageUtils(page);
  }
}
