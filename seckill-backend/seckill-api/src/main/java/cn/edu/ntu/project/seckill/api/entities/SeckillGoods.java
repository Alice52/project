package cn.edu.ntu.project.seckill.api.entities;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-06-01 23:21 <br>
 * @project seckill-backend <br>
 */
public class SeckillGoods {
  private Long id;
  private Long goodsId;
  private Integer stockCount;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  public Integer getStockCount() {
    return stockCount;
  }

  public void setStockCount(Integer stockCount) {
    this.stockCount = stockCount;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "SeckillGoods{"
        + "id="
        + id
        + ", goodsId="
        + goodsId
        + ", stockCount="
        + stockCount
        + ", startDate="
        + startDate
        + ", endDate="
        + endDate
        + '}';
  }
}
