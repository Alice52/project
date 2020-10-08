package ec.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.AttrAttrgroupRelationEntity;
import ec.product.repository.AttrAttrgroupRelationRepository;
import ec.product.service.AttrAttrgroupRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl
    extends ServiceImpl<AttrAttrgroupRelationRepository, AttrAttrgroupRelationEntity>
    implements AttrAttrgroupRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {

    IPage<AttrAttrgroupRelationEntity> page =
        this.page(
            new CommonQuery<AttrAttrgroupRelationEntity>().getPage(params),
            new QueryWrapper<AttrAttrgroupRelationEntity>());

    return new PageUtils(page);
  }
}
