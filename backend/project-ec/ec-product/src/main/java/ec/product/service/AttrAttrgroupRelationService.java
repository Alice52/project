package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.AttrAttrgroupRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

  PageUtils queryPage(Map<String, Object> params);

  void deleteByAttrIds(Long[] ids);

  void addRelations(Long groupId, List<Long> attrIds);
}
