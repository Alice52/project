package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberReceiveAddressEntity;
import ec.member.repository.MemberReceiveAddressRepository;
import ec.member.service.MemberReceiveAddressService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl
    extends ServiceImpl<MemberReceiveAddressRepository, MemberReceiveAddressEntity>
    implements MemberReceiveAddressService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<MemberReceiveAddressEntity> page =
        this.page(
            new CommonQuery<MemberReceiveAddressEntity>().getPage(params),
            new QueryWrapper<MemberReceiveAddressEntity>());

    return new PageUtils(page);
  }
}
