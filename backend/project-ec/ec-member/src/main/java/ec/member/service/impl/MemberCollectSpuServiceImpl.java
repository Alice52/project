package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberCollectSpuEntity;
import ec.member.repository.MemberCollectSpuRepository;
import ec.member.service.MemberCollectSpuService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberCollectSpuService")
public class MemberCollectSpuServiceImpl
    extends ServiceImpl<MemberCollectSpuRepository, MemberCollectSpuEntity>
    implements MemberCollectSpuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberCollectSpuEntity> page =
        this.page(
            new CommonQuery<MemberCollectSpuEntity>().getPage(params),
            new QueryWrapper<MemberCollectSpuEntity>());

    return new PageUtils(page);
  }
}
