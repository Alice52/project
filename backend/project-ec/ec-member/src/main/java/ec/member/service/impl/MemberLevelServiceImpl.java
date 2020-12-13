package ec.member.service.impl;

import cn.hutool.core.util.ObjectUtil;
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

/** @author zack */
@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelRepository, MemberLevelEntity>
    implements MemberLevelService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {

    QueryWrapper<MemberLevelEntity> wrapper = new QueryWrapper<>();

    Object key = params.get("key");
    if (ObjectUtil.isNotNull(key) || key instanceof String) {
      wrapper.like("name", key.toString());
    }

    IPage<MemberLevelEntity> page =
        this.page(new CommonQuery<MemberLevelEntity>().getPage(params), wrapper);

    return new PageUtils(page);
  }
}
