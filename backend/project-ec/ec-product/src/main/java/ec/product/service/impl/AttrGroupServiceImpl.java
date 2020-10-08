package ec.product.service.impl;

import cn.hutool.core.util.StrUtil;
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
  public PageUtils queryPage(Map<String, Object> params, Long catId) {

    if (catId == 0) {
      return this.queryPage(params);
    }

    // sql: select * from `pms_attr_group` where is_deleted = false and catelog_id= catId and
    // (attr_group_id =key or descript like %key%)
    QueryWrapper<AttrGroupEntity> queryWrapper =
        new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catId);
    String key = (String) params.get("key");
    if (StrUtil.isNotBlank(key)) {
      queryWrapper.and(obj -> obj.eq("attr_group_id", key).or().like("descript", key));
    }
    IPage<AttrGroupEntity> page =
        this.page(new CommonQuery<AttrGroupEntity>().getPage(params), queryWrapper);

    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrGroupEntity> page =
        this.page(
            new CommonQuery<AttrGroupEntity>().getPage(params),
            new QueryWrapper<AttrGroupEntity>());

    return new PageUtils(page);
  }
}
