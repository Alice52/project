package cn.edu.ntu.seckill.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2020-08-30 12:40 <br>
 * @project project-seckill <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class StockLogBO extends BaseBO {
  private String seckillGoodsId;
  private String goodsId;
  private Integer amount;
  private Integer status;

  public StockLogBO(String id) {
    super(id);
  }
}
