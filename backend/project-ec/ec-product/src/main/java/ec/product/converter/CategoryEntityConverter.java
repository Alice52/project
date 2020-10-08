package ec.product.converter;

import ec.product.entity.CategoryEntity;
import ec.product.model.CategoryEntityVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zack <br>
 * @create 2020-10-08 17:56 <br>
 * @project project-ec <br>
 */
@Mapper
public interface CategoryEntityConverter {

  CategoryEntityConverter INSTANCE = Mappers.getMapper(CategoryEntityConverter.class);

  /**
   * Convert po to vo.
   *
   * @param vo
   * @return
   */
  @Mapping(source = "sort", target = "sort", defaultValue = "0")
  CategoryEntityVO po2vo(CategoryEntity vo);

  /**
   * Convert pos to vos.
   *
   * @param vos
   * @return
   */
  List<CategoryEntityVO> pos2vos(List<CategoryEntity> vos);

  /**
   * Convert vo to po.
   *
   * @param categoryEntityVO
   * @return
   */
  CategoryEntity vo2po(CategoryEntityVO categoryEntityVO);
}
