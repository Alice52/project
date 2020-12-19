package ec.ware.converter;

import ec.ware.model.entity.PurchaseEntity;
import ec.ware.model.vo.PurchaseVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 采购信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Mapper
public interface PurchaseConverter {
  PurchaseConverter INSTANCE = Mappers.getMapper(PurchaseConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return
   */
  PurchaseEntity vo2po(PurchaseVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return
   */
  PurchaseVO po2vo(PurchaseEntity po);

  /**
   * Convert pos to vos.
   *
   * @param pos
   * @return
   */
  List<PurchaseVO> pos2vos(List<PurchaseEntity> pos);

  /**
   * Convert vos to pos.
   *
   * @param vos
   * @return
   */
  List<PurchaseEntity> vos2pos(List<PurchaseVO> vos);
}
