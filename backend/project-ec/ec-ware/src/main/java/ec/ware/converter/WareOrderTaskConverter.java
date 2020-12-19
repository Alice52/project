package ec.ware.converter;

import ec.ware.model.entity.WareOrderTaskEntity;
import ec.ware.model.vo.WareOrderTaskVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Mapper
public interface WareOrderTaskConverter {
  WareOrderTaskConverter INSTANCE = Mappers.getMapper(WareOrderTaskConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  WareOrderTaskEntity vo2po(WareOrderTaskVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  WareOrderTaskVO po2vo(WareOrderTaskEntity po);

  /**
   * Convert pos to vos.
   *
   * @param pos
   * @return
   */
  List<WareOrderTaskVO> pos2vos(List<WareOrderTaskEntity> pos);

  /**
   * Convert vos to pos.
   *
   * @param vos
   * @return
   */
  List<WareOrderTaskEntity> vos2pos(List<WareOrderTaskVO> vos);
}
