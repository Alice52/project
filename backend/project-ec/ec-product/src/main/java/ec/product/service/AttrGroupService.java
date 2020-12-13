package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.AttrGroupEntity;
import ec.product.model.vo.AdvicedAttrGroupVO;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

  PageUtils queryPage(Map<String, Object> params);

  PageUtils queryPage(Map<String, Object> params, Long catId);

  /**
   * Get all attr-group info included by catId, and contained all attr[base-attr] info.
   *
   * @param catId
   * @return AdvicedAttrGroupVO
   */
  List<AdvicedAttrGroupVO> getAdvicedGroupsByCatId(Long catId);
}
