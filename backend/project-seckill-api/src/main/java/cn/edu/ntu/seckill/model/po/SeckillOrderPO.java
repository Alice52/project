package cn.edu.ntu.seckill.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2020-08-11 22:02 <br>
 * @project project-seckill <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeckillOrderPO extends BasePO {

  private String userId;
  private String orderId;
  private String seckillGoodsId;
}
