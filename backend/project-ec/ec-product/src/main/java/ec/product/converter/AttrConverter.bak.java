// package ec.product.converter;
//
// import ec.product.converter.utils.AttrConverterUtils;
// import ec.product.entity.AttrEntity;
// import ec.product.model.vo.AttrEntityVO;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.Mappings;
// import org.mapstruct.factory.Mappers;
//
/// **
// * @author zack <br>
// * @create 2020-11-08 19:39 <br>
// * @project project-ec <br>
// */
// @Mapper(
//    uses = {AttrConverterUtils.class})
// public interface AttrConverter {
//
//  AttrConverter INSTANCE = Mappers.getMapper(AttrConverter.class);
//
//  /**
//   * Convert vo to po.
//   *
//   * @param vo
//   * @return
//   */
//  AttrEntity vo2po(AttrEntityVO vo);
//
//  /**
//   * Convert po to vo.
//   *
//   * @param po
//   * @return AttrEntityVO
//   */
//  @Mappings({
//    @Mapping(
//        target = "catelogName",
//        expression = "java(attrConverterUtils.getCateName(po.getCatelogId()))"),
//    @Mapping(
//        target = "attrGroupName",
//        expression = "java(attrConverterUtils.getAttrGroupName(po.getAttrId()))")
//  })
//  AttrEntityVO po2vo(AttrEntity po);
// }
