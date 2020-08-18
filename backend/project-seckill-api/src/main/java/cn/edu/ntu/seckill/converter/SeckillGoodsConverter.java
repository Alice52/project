package cn.edu.ntu.seckill.converter;

import cn.edu.ntu.seckill.enumeration.SeckillStatusEnum;
import cn.edu.ntu.seckill.model.bo.SeckillGoodsBO;
import cn.edu.ntu.seckill.model.po.SeckillGoodsPO;
import cn.edu.ntu.seckill.model.vo.SeckillGoodsVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-08-17 21:45 <br>
 * @project project-seckill <br>
 */
@Mapper
public interface SeckillGoodsConverter {

  SeckillGoodsConverter INSTANCE = Mappers.getMapper(SeckillGoodsConverter.class);

  /**
   * Convert vo to bo.
   *
   * @param vo
   * @return
   */
  SeckillGoodsBO vo2bo(SeckillGoodsVO vo);

  /**
   * Convert bo to po.
   *
   * @param bo
   * @return
   */
  SeckillGoodsPO bo2po(SeckillGoodsBO bo);

  /**
   * Convert po to bo.
   *
   * @param po
   * @return
   */
  SeckillGoodsBO po2bo(SeckillGoodsPO po);

  /**
   * Convert bo to po.
   *
   * @param bo
   * @return
   */
  @Mapping(target = "status", expression = "java(initStatus(bo))")
  SeckillGoodsVO bo2vo(SeckillGoodsBO bo);

  /**
   * Convert bos to vos.
   *
   * @param bos
   * @return
   */
  List<SeckillGoodsVO> bos2vos(List<SeckillGoodsBO> bos);

  /**
   * Convert pos to bos.
   *
   * @param pos
   * @return
   */
  List<SeckillGoodsBO> pos2bos(List<SeckillGoodsPO> pos);

  /**
   * convert pos to vos.
   *
   * @param pos
   * @return
   */
  @Deprecated
  List<SeckillGoodsVO> pos2vos(List<SeckillGoodsPO> pos);

  /**
   * Init seckill status.
   *
   * @param bo
   * @return
   */
  default SeckillStatusEnum initStatus(SeckillGoodsBO bo) {

    LocalDateTime now = LocalDateTime.now();
    if (bo.getStartDate().isBefore(now)) {
      return SeckillStatusEnum.NOT_STARTED;
    }

    if (bo.getEndDate().isAfter(now)) {
      return SeckillStatusEnum.FINISHED;
    }

    return SeckillStatusEnum.ON_GOING;
  }
}
