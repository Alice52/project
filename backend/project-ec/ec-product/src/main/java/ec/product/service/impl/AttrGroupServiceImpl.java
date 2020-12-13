package ec.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.constants.ProductionConstants;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.converter.AttrGroupConverter;
import ec.product.entity.AttrGroupEntity;
import ec.product.model.vo.AdvicedAttrGroupVO;
import ec.product.model.vo.AttrGroupVO;
import ec.product.model.vo.AttrVO;
import ec.product.repository.AttrGroupRepository;
import ec.product.service.AttrGroupService;
import ec.product.service.AttrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zack
 * @email zzhang_xz@163.com
 */
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupRepository, AttrGroupEntity>
    implements AttrGroupService {

  @Resource private AttrService attrService;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrGroupEntity> page =
        this.page(new CommonQuery<AttrGroupEntity>().getPage(params), new QueryWrapper<>());

    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params, Long catId) {
    QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
    String key = (String) params.get("key");
    if (StrUtil.isNotBlank(key)) {
      queryWrapper.and(obj -> obj.eq("attr_group_id", key).or().like("descript", key));
    }

    if (catId != 0) {
      queryWrapper.eq("catelog_id", catId);
    }
    // sql: select * from `pms_attr_group` where is_deleted = false and catelog_id= catId and
    // (attr_group_id =key or descript like %key%)
    IPage<AttrGroupEntity> page =
        this.page(new CommonQuery<AttrGroupEntity>().getPage(params), queryWrapper);

    return new PageUtils(page);
  }

  @Override
  public List<AdvicedAttrGroupVO> getAdvicedGroupsByCatId(Long catId) {
    List<AttrGroupEntity> groupEntities =
        baseMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catId));

    List<AdvicedAttrGroupVO> advicedGroupVO =
        groupEntities.stream().map(x -> convert2AdvicedGroups(x)).collect(Collectors.toList());

    return advicedGroupVO;
  }

  private AdvicedAttrGroupVO convert2AdvicedGroups(AttrGroupEntity entity) {
    List<AttrVO> attrVOS = attrService.getByGroupId(entity.getAttrGroupId());
    AttrGroupVO groupVO = AttrGroupConverter.INSTANCE.po2vo(entity);

    return new AdvicedAttrGroupVO(
        groupVO,
        ObjectUtil.isNull(attrVOS)
            ? null
            : attrVOS.stream()
                .filter(
                    x -> x.getAttrType() == ProductionConstants.AttrEnum.ATTR_TYPE_BASE.getCode())
                .collect(Collectors.toList()));
  }
}
