package ec.product.converter.utils;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ec.product.converter.AttrConverter;
import ec.product.entity.AttrAttrgroupRelationEntity;
import ec.product.entity.AttrEntity;
import ec.product.entity.AttrGroupEntity;
import ec.product.entity.CategoryEntity;
import ec.product.model.vo.AttrEntityVO;
import ec.product.service.AttrAttrgroupRelationService;
import ec.product.service.AttrGroupService;
import ec.product.service.CategoryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-11-09 00:10 <br>
 * @project project-ec <br>
 */
@Component
public class AttrConverterUtils {

  @Resource private CategoryService categoryService;
  @Resource private AttrAttrgroupRelationService relationService;
  @Resource private AttrGroupService attrGroupService;

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  public AttrEntityVO convertToVO(AttrEntity po) {

    if (ObjectUtil.isNull(po)) {
      return null;
    }

    AttrEntityVO entityVO = AttrConverter.INSTANCE.po2vo(po);
    entityVO.setCatelogName(getCateName(entityVO.getCatelogId()));
    final AttrGroupEntity attrGroup = getAttrGroup(po.getAttrId());
    entityVO.setAttrGroupName(attrGroup.getAttrGroupName());
    entityVO.setAttrGroupId(attrGroup.getAttrGroupId());

    return entityVO;
  }

  /**
   * Get catelogName by catelogId
   *
   * @param cateId
   * @return
   */
  public String getCateName(Long cateId) {
    CategoryEntity entity = categoryService.getById(cateId);
    Assert.notNull(entity, "Has no category which id is {}.", cateId);
    return entity.getName();
  }

  /**
   * // TODO: error, this is error, attr and group is n : n
   *
   * @param attrId
   * @return
   */
  @Deprecated
  public AttrGroupEntity getAttrGroup(Long attrId) {
    final AttrAttrgroupRelationEntity relationEntity =
        relationService.getOne(
            new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
    Assert.notNull(relationEntity, "Has no group which id is {}.", attrId);
    final AttrGroupEntity groupEntity = attrGroupService.getById(relationEntity.getAttrGroupId());
    Assert.notNull(groupEntity, "Has no group which id is {}.", attrId);

    return groupEntity;
  }
}
