package ec.coupon.converter;

import ec.coupon.entity.SpuBoundsEntity;
import ec.coupon.model.to.SpuBoundTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Mapper
public interface SpuBoundConverter {
  SpuBoundConverter INSTANCE = Mappers.getMapper(SpuBoundConverter.class);

  /**
   * Convert to to po.
   *
   * @param to
   * @return SpuBoundsEntity
   */
  SpuBoundsEntity to2po(SpuBoundTO to);
}
