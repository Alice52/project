package ec.ware.converter;

import ec.ware.model.entity.WareSkuEntity;
import ec.ware.model.vo.WareSkuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品库存
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Mapper
public interface WareSkuConverter {
  WareSkuConverter INSTANCE = Mappers.getMapper(WareSkuConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  WareSkuEntity vo2po(WareSkuVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  WareSkuVO po2vo(WareSkuEntity po);

  /**
   * Convert pos to vos.
   *
   * @param pos
   * @return
   */
  List<WareSkuVO> pos2vos(List<WareSkuEntity> pos);

  /**
   * Convert vos to pos.
   *
   * @param vos
   * @return
   */
  List<WareSkuEntity> vos2pos(List<WareSkuVO> vos);
}
