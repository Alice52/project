package ec.product.converter;

import ec.product.entity.AttrEntity;
import ec.product.model.vo.AttrEntityVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020-11-08 19:39 <br>
 * @project project-ec <br>
 */
@Mapper
public interface AttrConverter {

  AttrConverter INSTANCE = Mappers.getMapper(AttrConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  AttrEntity vo2po(AttrEntityVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return AttrEntityVO
   */
  AttrEntityVO po2vo(AttrEntity po);
}
