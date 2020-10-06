package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberCollectSubjectEntity;
import ec.member.repository.MemberCollectSubjectRepository;
import ec.member.service.MemberCollectSubjectService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberCollectSubjectService")
public class MemberCollectSubjectServiceImpl
    extends ServiceImpl<MemberCollectSubjectRepository, MemberCollectSubjectEntity>
    implements MemberCollectSubjectService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberCollectSubjectEntity> page =
        this.page(
            new CommonQuery<MemberCollectSubjectEntity>().getPage(params),
            new QueryWrapper<MemberCollectSubjectEntity>());

    return new PageUtils(page);
  }
}
