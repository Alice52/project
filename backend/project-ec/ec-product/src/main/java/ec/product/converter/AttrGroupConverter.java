package ec.product.converter;

import ec.product.entity.AttrGroupEntity;
import ec.product.model.vo.AdvicedAttrGroupVO;
import ec.product.model.vo.AttrGroupVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020-10-08 17:56 <br>
 * @project project-ec <br>
 */
@Mapper
public interface AttrGroupConverter {

  AttrGroupConverter INSTANCE = Mappers.getMapper(AttrGroupConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  @Mapping(source = "sort", target = "sort", defaultValue = "0")
  AttrGroupEntity vo2po(AttrGroupVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  @Mapping(target = "catelogPath", ignore = true)
  AttrGroupVO po2vo(AttrGroupEntity po);
}
