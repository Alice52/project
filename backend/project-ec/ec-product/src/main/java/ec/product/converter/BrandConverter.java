package ec.product.converter;

import ec.product.entity.BrandEntity;
import ec.product.model.vo.BrandVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020-10-08 17:56 <br>
 * @project project-ec <br>
 */
@Mapper
public interface BrandConverter {

  BrandConverter INSTANCE = Mappers.getMapper(BrandConverter.class);

  /**
   * Convert vo to po.
   *
   * @param brand
   * @return
   */
  @Mapping(source = "showStatus", target = "showStatus", defaultValue = "1")
  BrandEntity vo2po(BrandVO brand);

  /**
   * Convert vo to po.
   *
   * @param brand
   * @return BrandVO
   */
  BrandVO po2vo(BrandEntity brand);
}
