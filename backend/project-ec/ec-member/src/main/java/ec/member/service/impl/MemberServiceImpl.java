package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberEntity;
import ec.member.repository.MemberRepository;
import ec.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberRepository, MemberEntity>
    implements MemberService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberEntity> page =
        this.page(
            new CommonQuery<MemberEntity>().getPage(params), new QueryWrapper<MemberEntity>());

    return new PageUtils(page);
  }
}
