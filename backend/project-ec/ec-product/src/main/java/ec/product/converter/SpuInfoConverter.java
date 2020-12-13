package ec.product.converter;

import ec.product.entity.SpuInfoEntity;
import ec.product.model.vo.SpuSaveVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Mapper
public interface SpuInfoConverter {
  SpuInfoConverter INSTANCE = Mappers.getMapper(SpuInfoConverter.class);

  /**
   * Convert spu info vo to spu entity
   *
   * @param vo
   * @return SpuInfoEntity
   */
  SpuInfoEntity vo2po(SpuSaveVO vo);
}
