package ec.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.AttrGroupEntity;
import ec.product.repository.AttrGroupRepository;
import ec.product.service.AttrGroupService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupRepository, AttrGroupEntity>
    implements AttrGroupService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrGroupEntity> page =
        this.page(
            new CommonQuery<AttrGroupEntity>().getPage(params),
            new QueryWrapper<AttrGroupEntity>());

    return new PageUtils(page);
  }
}
