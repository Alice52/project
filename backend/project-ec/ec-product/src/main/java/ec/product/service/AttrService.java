package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.AttrEntity;
import ec.product.model.vo.AttrVO;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
public interface AttrService extends IService<AttrEntity> {

  PageUtils queryPage(Map<String, Object> params);

  boolean saveAttr(AttrVO vo);

  PageUtils queryPage(Map<String, Object> params, boolean attrtype, long catId);

  AttrVO getAttrInfo(Long attrId);

  boolean updateAttr(AttrVO vo);

  boolean removeAttrByIds(List<Long> attrIds);

  boolean removeAttrById(Long attrId);

  /**
   * Get all attrs according to the groupId.
   *
   * @param groupId
   * @return
   */
  List<AttrVO> getByGroupId(Long groupId);
}
