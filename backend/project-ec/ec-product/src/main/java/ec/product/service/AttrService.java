package ec.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.product.entity.AttrEntity;
import ec.product.model.vo.AttrEntityVO;

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

  boolean saveAttr(AttrEntityVO vo);

  PageUtils queryPage(Map<String, Object> params, boolean attrtype, long catId);

  AttrEntityVO getAttrInfo(Long attrId);

  boolean updateAttr(AttrEntityVO vo);
}
