package ec.ware.converter;

import ec.ware.model.entity.WareOrderTaskDetailEntity;
import ec.ware.model.vo.WareOrderTaskDetailVO;
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
public interface WareOrderTaskDetailConverter {
  WareOrderTaskDetailConverter INSTANCE = Mappers.getMapper(WareOrderTaskDetailConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  WareOrderTaskDetailEntity vo2po(WareOrderTaskDetailVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  WareOrderTaskDetailVO po2vo(WareOrderTaskDetailEntity po);

  /**
   * Convert pos to vos.
   *
   * @param pos
   * @return
   */
  List<WareOrderTaskDetailVO> pos2vos(List<WareOrderTaskDetailEntity> pos);

  /**
   * Convert vos to pos.
   *
   * @param vos
   * @return
   */
  List<WareOrderTaskDetailEntity> vos2pos(List<WareOrderTaskDetailVO> vos);
}
