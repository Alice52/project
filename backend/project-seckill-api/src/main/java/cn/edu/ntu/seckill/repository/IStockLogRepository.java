package cn.edu.ntu.seckill.repository;

import cn.edu.ntu.seckill.model.bo.StockLogBO;
import cn.edu.ntu.seckill.model.po.StockLogPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-30 12:42 <br>
 * @project project-seckill <br>
 */
@Mapper
public interface IStockLogRepository {

  /**
   * create stock logs about seckill goods.
   *
   * @param stockLog
   */
  void create(StockLogPO stockLog);

  /**
   * Get by stockLogId.
   *
   * @param stockLogId
   * @return
   */
  StockLogPO getByPK(@NotBlank @Param("id") String stockLogId);

  /**
   * Update stock log about status.
   *
   * @param stockLogId
   * @param status
   */
  void updateStatus(@NotBlank @Param("id") String stockLogId, @NotNull @Param("status") Integer status);
}
