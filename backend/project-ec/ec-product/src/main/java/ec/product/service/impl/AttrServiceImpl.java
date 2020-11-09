package ec.product.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.converter.AttrConverter;
import ec.product.converter.utils.AttrConverterUtils;
import ec.product.entity.AttrAttrgroupRelationEntity;
import ec.product.entity.AttrEntity;
import ec.product.entity.AttrGroupEntity;
import ec.product.model.vo.AttrEntityVO;
import ec.product.repository.AttrRepository;
import ec.product.service.AttrAttrgroupRelationService;
import ec.product.service.AttrGroupService;
import ec.product.service.AttrService;
import ec.product.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zack
 * @date
 */
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrRepository, AttrEntity>
    implements AttrService {

  @Resource private CategoryService categoryService;
  @Resource private AttrAttrgroupRelationService attrAttrgroupRelationService;
  @Resource private AttrConverterUtils attrConverterUtils;
  @Resource private AttrGroupService attrGroupService;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrEntity> page =
        this.page(new CommonQuery<AttrEntity>().getPage(params), new QueryWrapper<>());

    return new PageUtils(page);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean saveAttr(AttrEntityVO vo) {
    validateGroupInfo(vo.getAttrGroupId());

    // 1. create record og attr
    AttrEntity entity = AttrConverter.INSTANCE.vo2po(vo);
    validateExistence(entity.getAttrName(), entity.getCatelogId());
    this.save(entity);

    // 2. create relation ship
    AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
    relationEntity.setAttrGroupId(vo.getAttrGroupId());
    relationEntity.setAttrId(entity.getAttrId());

    final boolean success = attrAttrgroupRelationService.save(relationEntity);

    return success;
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params, boolean attrtype, long catId) {
    QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();

    final Object key = params.get("key");
    if (ObjectUtil.isNotEmpty(key)) {
      wrapper.and(obj -> obj.like("attr_name", key).or().like("value_select", key));
    }
    if (ObjectUtil.isEmpty(catId)) {
      wrapper.eq("catelog_id", catId);
    }
    wrapper.eq("attr_type", attrtype);

    IPage<AttrEntity> page = this.page(new CommonQuery<AttrEntity>().getPage(params), wrapper);
    PageUtils pageUtils = new PageUtils(page);
    List<AttrEntity> records = page.getRecords();
    List<AttrEntityVO> entityVOS =
        records.stream().map(x -> attrConverterUtils.convertToVO(x)).collect(Collectors.toList());
    pageUtils.setList(entityVOS);

    return pageUtils;
  }

  @Override
  public AttrEntityVO getAttrInfo(Long attrId) {
    AttrEntityVO vo = attrConverterUtils.convertToVO(this.getById(attrId));
    vo.setCatelogPath(categoryService.findCatelogPath(vo.getCatelogId()));

    return vo;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean updateAttr(AttrEntityVO vo) {

    validateGroupInfo(vo.getAttrGroupId());

    // 1. update entity
    AttrEntity entity = AttrConverter.INSTANCE.vo2po(vo);
    validateExistence(entity.getAttrName(), entity.getCatelogId(), vo.getAttrId());
    this.updateById(entity);

    // 2. update relation ship
    AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
    relationEntity.setAttrGroupId(vo.getAttrGroupId());
    relationEntity.setAttrId(entity.getAttrId());

    UpdateWrapper<AttrAttrgroupRelationEntity> updateWrapper = new UpdateWrapper<>();
    // TODO: error handling
    updateWrapper.eq("attr_id", vo.getAttrId()).set("attr_group_id", vo.getAttrGroupId());

    return attrAttrgroupRelationService.update(updateWrapper);
  }

  private void validateGroupInfo(Long attrGroupId) {
    QueryWrapper<AttrGroupEntity> wrapper =
        new QueryWrapper<AttrGroupEntity>().eq("attr_group_id", attrGroupId);
    validateGroupInfo(wrapper, attrGroupId);
  }

  private void validateGroupInfo(QueryWrapper<AttrGroupEntity> wrapper, long attrGroupId) {
    Assert.notNull(attrGroupId, "attr must belong to specific group");

    int count = attrGroupService.count(wrapper);
    Assert.isTrue(
        count > 0, "Has no attr group which id is {}, please check and try again", attrGroupId);
  }

  private void validateExistence(String attrName, Long catelogId) {
    QueryWrapper wrapper =
        new QueryWrapper<AttrEntity>().eq("attr_name", attrName).eq("catelog_id", catelogId);

    validateAttrExiestence(wrapper);
  }

  private void validateExistence(String attrName, Long catelogId, Long AttrId) {

    QueryWrapper wrapper =
        new QueryWrapper<AttrEntity>()
            .eq("attr_name", attrName)
            .eq("catelog_id", catelogId)
            .ne("attr_id", AttrId);

    validateAttrExiestence(wrapper);
  }

  private void validateAttrExiestence(QueryWrapper<AttrEntity> wrapper) {

    final int count = this.count(wrapper);
    Assert.isTrue(count == 0, "attr name is exist, please check and try again");
  }
}
