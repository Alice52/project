package ec.product.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Data
@NoArgsConstructor
public class Skus {
  private List<Attr> attr = new ArrayList<>();
  private String skuName;
  private BigDecimal price;
  private String skuTitle;
  private String skuSubtitle;
  private List<Images> images = new ArrayList<>();
  private List<String> descar = new ArrayList<>();
  private int fullCount;
  private BigDecimal discount;
  private int countStatus;
  private BigDecimal fullPrice;
  private BigDecimal reducePrice;
  private int priceStatus;
  private List<MemberPrice> memberPrice = new ArrayList<>();
}
