package cn.edu.ntu.seckill.converter;

import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.po.GoodsPO;
import cn.edu.ntu.seckill.model.vo.GoodsVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zack <br>
 * @create 2020-08-11 22:15 <br>
 * @project project-seckill <br>
 */
@Mapper(imports = {cn.hutool.core.util.IdUtil.class})
public interface GoodsConverter {

  GoodsConverter INSTANCE = Mappers.getMapper(GoodsConverter.class);

  /**
   * Convert vo to bo.
   *
   * @param vo
   * @return
   */
  GoodsBO vo2bo(GoodsVO vo);

  /**
   * Convert bo to po.
   *
   * @param goodsBO
   * @return
   */
  @Mapping(target = "id", expression = "java(IdUtil.fastSimpleUUID())")
  GoodsPO bo2po(GoodsBO goodsBO);

  /**
   * Convert po to bo.
   *
   * @param po
   * @return
   */
  @Mapping(target = "image", source = "img")
  GoodsBO po2bo(GoodsPO po);

  /**
   * Convert bo to po.
   *
   * @param bo
   * @return
   */
  GoodsVO bo2vo(GoodsBO bo);
}
