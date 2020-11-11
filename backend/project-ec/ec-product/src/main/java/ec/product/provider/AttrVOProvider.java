package ec.product.provider;

import ec.common.constants.ProductionConstants;
import ec.product.model.vo.AttrVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * This provider is validate attr_type and group_id relation <br>
 * - if attr_type is base type, group_id will not be null<br>
 * - if attr_type is sale type, group_id will has no limit<br>
 *
 * @author zack <br>
 * @create 2020-08-02 12:26 <br>
 * @project validation <br>
 */
public class AttrVOProvider implements DefaultGroupSequenceProvider<AttrVO> {

  @Override
  public List<Class<?>> getValidationGroups(AttrVO object) {

    List<Class<?>> defaultGroups = new ArrayList<>();
    defaultGroups.add(AttrVO.class);

    if (null == object || null == object.getAttrType()) {
      return defaultGroups;
    }

    if (object.getAttrType().equals(ProductionConstants.AttrEnum.ATTR_TYPE_BASE.getCode())) {
      defaultGroups.add(AttrVO.BaseAttrType.class);
    } else {
      defaultGroups.add(AttrVO.SaleAttrType.class);
    }

    return defaultGroups;
  }
}
