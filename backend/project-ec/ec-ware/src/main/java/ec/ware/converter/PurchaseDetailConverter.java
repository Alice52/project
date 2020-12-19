package ec.ware.converter;

import ec.ware.model.entity.PurchaseDetailEntity;
import ec.ware.model.vo.PurchaseDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Mapper
public interface PurchaseDetailConverter {
  PurchaseDetailConverter INSTANCE = Mappers.getMapper(PurchaseDetailConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  PurchaseDetailEntity vo2po(PurchaseDetailVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  PurchaseDetailVO po2vo(PurchaseDetailEntity po);

  /**
   * Convert pos to vos.
   *
   * @param pos
   * @return
   */
  List<PurchaseDetailVO> pos2vos(List<PurchaseDetailEntity> pos);

  /**
   * Convert vos to pos.
   *
   * @param vos
   * @return
   */
  List<PurchaseDetailEntity> vos2pos(List<PurchaseDetailVO> vos);
}
