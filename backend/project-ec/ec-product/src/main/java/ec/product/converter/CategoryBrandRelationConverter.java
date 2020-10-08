package ec.product.converter;

import ec.product.entity.CategoryBrandRelationEntity;
import ec.product.model.CategoryBrandRelationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zack <br>
 * @create 2020-10-08 17:56 <br>
 * @project project-ec <br>
 */
@Mapper
public interface CategoryBrandRelationConverter {

  CategoryBrandRelationConverter INSTANCE = Mappers.getMapper(CategoryBrandRelationConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  CategoryBrandRelationEntity vo2po(CategoryBrandRelationVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  CategoryBrandRelationVO po2vo(CategoryBrandRelationEntity po);

  /**
   * Convert pos to vos.
   *
   * @param pos
   * @return
   */
  List<CategoryBrandRelationVO> pos2vos(List<CategoryBrandRelationEntity> pos);
}
