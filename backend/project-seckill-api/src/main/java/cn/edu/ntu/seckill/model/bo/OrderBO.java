package cn.edu.ntu.seckill.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-30 11:06 <br>
 * @project project-seckill <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBO extends BaseBO {
  private String userId;
  private String goodsId;
  private String deliveryAddrId;
  private Integer count;
  private String channel;
  private String status;
  private LocalDateTime payDate;

  /** these columns are used for seckill-order. */
  private String orderId;
  private String seckillGoodsId;

  /** these columns are redudant for ui show. */
  private String goodsName;
  private BigDecimal seckillPrice;
  private BigDecimal price;
}
