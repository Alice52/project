package ec.product.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.constants.ProductionConstants;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.converter.AttrConverter;
import ec.product.converter.utils.AttrConverterUtils;
import ec.product.entity.AttrAttrgroupRelationEntity;
import ec.product.entity.AttrEntity;
import ec.product.entity.AttrGroupEntity;
import ec.product.model.vo.AttrVO;
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

    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Resource
    private AttrConverterUtils attrConverterUtils;
    @Resource
    private AttrGroupService attrGroupService;
    @Resource
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page =
                this.page(new CommonQuery<AttrEntity>().getPage(params), new QueryWrapper<>());

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAttr(AttrVO vo) {
        validateGroupInfo(vo.getAttrGroupId());

        // 1. create record og attr
        AttrEntity entity = AttrConverter.INSTANCE.vo2po(vo);
        validateExistence(entity.getAttrName(), entity.getCatelogId());
        this.save(entity);

        if (vo.getAttrType() == ProductionConstants.AttrEnum.ATTR_TYPE_SALE.getCode()) {
            return true;
        }

        // 2. create relation ship
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(vo.getAttrGroupId());
        relationEntity.setAttrId(entity.getAttrId());

        final boolean success = attrAttrgroupRelationService.save(relationEntity);

        return success;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, boolean attrType, long catId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();

        final Object key = params.get("key");
        if (ObjectUtil.isNotEmpty(key)) {
            wrapper.and(obj -> obj.like("attr_name", key).or().like("value_select", key));
        }
        if (catId != 0) {
            wrapper.eq("catelog_id", catId);
        }
        wrapper.eq("attr_type", attrType);

        return getPageUtils(params, wrapper);
    }

    @Override
    public AttrVO getAttrInfo(Long attrId) {
        AttrVO vo = attrConverterUtils.convertToVO(this.getById(attrId));
        vo.setCatelogPath(categoryService.findCatelogPath(vo.getCatelogId()));

        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAttr(AttrVO vo) {

        validateGroupInfo(vo.getAttrGroupId());

        // 1. update entity
        AttrEntity entity = AttrConverter.INSTANCE.vo2po(vo);
        validateExistence(entity.getAttrName(), entity.getCatelogId(), vo.getAttrId());
        if (vo.getAttrType() == ProductionConstants.AttrEnum.ATTR_TYPE_SALE.getCode()) {
            return this.updateById(entity);
        }
        // 2. update relation ship
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(vo.getAttrGroupId());
        relationEntity.setAttrId(entity.getAttrId());

        UpdateWrapper<AttrAttrgroupRelationEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("attr_id", vo.getAttrId()).set("attr_group_id", vo.getAttrGroupId());

        return attrAttrgroupRelationService.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeAttrByIds(List<Long> attrIds) {

        final boolean remove = this.removeByIds(attrIds);
        attrAttrgroupRelationService.remove(
                new UpdateWrapper<AttrAttrgroupRelationEntity>().in("attr_id", attrIds));

        return remove;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeAttrById(Long attrId) {

        final boolean remove = this.removeById(attrId);
        attrAttrgroupRelationService.remove(
                new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));

        return remove;
    }

    @Override
    public List<AttrVO> getByGroupId(Long groupId) {

    /*
    List<AttrAttrgroupRelationEntity> list =
        attrAttrgroupRelationService.list(
            new QueryWrapper<AttrAttrgroupRelationEntity>()
                .eq("attr_group_id", groupId)
                .select("attr_id"));
    */

    /*
      // TODO: this is strange
     LambdaQueryWrapper wrapper = Wrappers.lambdaQuery(AttrAttrgroupRelationEntity.class)
        .select(x -> "attr_id".equals(x.getColumn()));
     List list = attrAttrgroupRelationService.getBaseMapper().selectObjs(wrapper);
     Object collect = list.stream().map(o -> (Integer) o).collect(Collectors.toList());

     List<Object> list = attrAttrgroupRelationService.getBaseMapper().selectObjs(wrapper);
     List<Integer> collect = list.stream().map(o -> (Integer) o).collect(Collectors.toList());
    */
        LambdaQueryWrapper<AttrAttrgroupRelationEntity> wrapper =
                Wrappers.lambdaQuery(AttrAttrgroupRelationEntity.class)
                        .eq(AttrAttrgroupRelationEntity::getAttrGroupId, groupId)
                        .select(AttrAttrgroupRelationEntity::getAttrId);

        List<Long> attrIds =
                attrAttrgroupRelationService.getBaseMapper().selectObjs(wrapper).stream()
                        .map(o -> (Long) o)
                        .collect(Collectors.toList());

        List<AttrEntity> attrEntities = attrService.listByIds(attrIds);

        return AttrConverter.INSTANCE.pos2vos(attrEntities);
    }

    @Override
    public PageUtils queryNoRelationAttrsByGroupIdPage(Map<String, Object> params, Long groupId) {

        /**
         * select * from AttrGroupEntity where catelog_id =
         *      (select catelog_id from AttrGroupEntity where groupId = ?)
         */

        // 1. get catId by groupId
        AttrGroupEntity groupEntity = attrGroupService.getById(groupId);
        Long catelogId = groupEntity.getCatelogId();
        // 2. get all linked attrs according to catId
        List<Long> groupIds = attrGroupService.list(new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", catelogId))
                //.ne("group_id", groupId)
                .stream().map(x -> x.getAttrGroupId()).collect(Collectors.toList());

        List<Long> attrIds = attrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>()
                .in("attr_group_id", groupIds))
                .stream()
                .map(x -> x.getAttrId())
                .collect(Collectors.toList());

        // 3. exclude this groupId attrs and linked attrs
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(attrIds) && attrIds.size() > 0) {
            wrapper.notIn("attr_id", attrIds);
        }
        wrapper.eq("catelog_id", catelogId);

        final Object key = params.get("key");
        if (ObjectUtil.isNotEmpty(key)) {
            wrapper.and(obj -> obj.eq("attr_id", key).or().like("attr_name", key));
        }
        return getPageUtils(params, wrapper);
    }

    private PageUtils getPageUtils(Map<String, Object> params, QueryWrapper<AttrEntity> wrapper) {
        IPage<AttrEntity> page = this.page(new CommonQuery<AttrEntity>().getPage(params), wrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrVO> entityVOS =
                records.stream().map(x -> attrConverterUtils.convertToVO(x)).collect(Collectors.toList());
        pageUtils.setList(entityVOS);

        return pageUtils;
    }

    private void validateGroupInfo(Long attrGroupId) {
        if (ObjectUtil.isNull(attrGroupId)) {
            return;
        }
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

        validateAttrExistence(wrapper);
    }

    private void validateExistence(String attrName, Long catelogId, Long attrId) {

        QueryWrapper wrapper =
                new QueryWrapper<AttrEntity>()
                        .eq("attr_name", attrName)
                        .eq("catelog_id", catelogId)
                        .ne("attr_id", attrId);

        validateAttrExistence(wrapper);
    }

    private void validateAttrExistence(QueryWrapper<AttrEntity> wrapper) {

        final int count = this.count(wrapper);
        Assert.isTrue(count == 0, "attr name is exist, please check and try again");
    }
}
