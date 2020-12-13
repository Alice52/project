package ec.coupon.converter;

import ec.coupon.entity.MemberPriceEntity;
import ec.coupon.model.to.MemberPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Mapper
public interface MemberPriceConverter {

  MemberPriceConverter INSTANCE = Mappers.getMapper(MemberPriceConverter.class);

  /**
   * Convert MemberPriceTO to MemberPriceEntity
   *
   * @param to
   * @return MemberPriceEntity
   */
  @Mappings({
    @Mapping(target = "memberLevelId", source = "to.id"),
    @Mapping(target = "memberLevelName", source = "to.name"),
    @Mapping(target = "memberPrice", source = "to.price"),
    @Mapping(target = "addOther", defaultValue = "1"),
    @Mapping(target = "skuId", source = "skuId")
  })
  MemberPriceEntity to2po(MemberPrice to, Long skuId);
}
