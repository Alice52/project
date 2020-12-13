package ec.coupon.converter;

import ec.coupon.entity.SkuFullReductionEntity;
import ec.coupon.model.to.SkuReductionTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Mapper
public interface SkuFullReductionConverter {
  SkuFullReductionConverter INSTANCE = Mappers.getMapper(SkuFullReductionConverter.class);

  /**
   * Convert to to po.
   *
   * @param to
   * @return
   */
  SkuFullReductionEntity to2po(SkuReductionTO to);
}
