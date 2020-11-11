package ec.product.converter;

import ec.product.entity.AttrEntity;
import ec.product.model.vo.AttrVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
  AttrEntity vo2po(AttrVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return AttrEntityVO
   */
  @Mappings({
    @Mapping(target = "catelogPath", ignore = true),
    @Mapping(target = "attrGroupId", ignore = true),
    @Mapping(target = "attrGroupName", ignore = true),
    @Mapping(target = "catelogName", ignore = true)
  })
  AttrVO po2vo(AttrEntity po);

  List<AttrVO> pos2vos(List<AttrEntity> pos);
}
