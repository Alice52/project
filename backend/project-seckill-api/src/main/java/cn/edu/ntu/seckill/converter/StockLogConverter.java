package cn.edu.ntu.seckill.converter;

import cn.edu.ntu.seckill.model.bo.StockLogBO;
import cn.edu.ntu.seckill.model.po.StockLogPO;
import cn.hutool.core.util.IdUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020-08-30 13:23 <br>
 * @project project-seckill <br>
 */
@Mapper(imports = IdUtil.class)
public interface StockLogConverter {

  StockLogConverter INSTANCE = Mappers.getMapper(StockLogConverter.class);

  /**
   * Convert po to bo.
   *
   * @param po
   * @return
   */
  StockLogBO po2bo(StockLogPO po);

  /**
   * Convert bo to po.
   *
   * @param stockLog
   * @return
   */
  StockLogPO bo2po(StockLogBO stockLog);
}
