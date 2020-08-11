package cn.edu.ntu.seckill.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-11 21:54 <br>
 * @project project-seckill <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPO extends BasePO {

  private String userId;
  private String goodsId;
  private String deliveryAddrId;
  private String goodsName;
  private Integer count;
  private String price;
  private String channel;
  private String status;
  private LocalDateTime payDate;
}
