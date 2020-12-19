package ec.ware.converter;

import ec.ware.model.entity.WareInfoEntity;
import ec.ware.model.vo.WareInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 仓库信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Mapper
public interface WareInfoConverter {
  WareInfoConverter INSTANCE = Mappers.getMapper(WareInfoConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  WareInfoEntity vo2po(WareInfoVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  WareInfoVO po2vo(WareInfoEntity po);

  /**
   * Convert pos to vos.
   *
   * @param pos
   * @return
   */
  List<WareInfoVO> pos2vos(List<WareInfoEntity> pos);

  /**
   * Convert vos to pos.
   *
   * @param vos
   * @return
   */
  List<WareInfoEntity> vos2pos(List<WareInfoVO> vos);
}
