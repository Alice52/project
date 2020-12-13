package ec.coupon.converter;

import ec.coupon.entity.SkuLadderEntity;
import ec.coupon.model.to.SkuReductionTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Mapper
public interface SkuLadderConverter {
  SkuLadderConverter INSTANCE = Mappers.getMapper(SkuLadderConverter.class);

  /**
   * Convert SkuReductionTO to SkuLadderEntity
   *
   * @param to
   * @return SkuLadderEntity
   */
  SkuLadderEntity to2po(SkuReductionTO to);
}
