package cn.edu.ntu.project.seckill.api.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-06-29 20:08 <br>
 * @project seckill-backend <br>
 */
public class GoodsVo {

  private String id;
  private String goodsName;
  private String goodsTitle;
  private String goodsImg;
  private String goodsDetail;
  private BigDecimal goodsPrice;
  private Integer goodsStock;
  private BigDecimal seckillPrice;
  private Integer stockCount;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getGoodsTitle() {
    return goodsTitle;
  }

  public void setGoodsTitle(String goodsTitle) {
    this.goodsTitle = goodsTitle;
  }

  public String getGoodsImg() {
    return goodsImg;
  }

  public void setGoodsImg(String goodsImg) {
    this.goodsImg = goodsImg;
  }

  public String getGoodsDetail() {
    return goodsDetail;
  }

  public void setGoodsDetail(String goodsDetail) {
    this.goodsDetail = goodsDetail;
  }

  public BigDecimal getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(BigDecimal goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

  public Integer getGoodsStock() {
    return goodsStock;
  }

  public void setGoodsStock(Integer goodsStock) {
    this.goodsStock = goodsStock;
  }

  public BigDecimal getSeckillPrice() {
    return seckillPrice;
  }

  public void setSeckillPrice(BigDecimal seckillPrice) {
    this.seckillPrice = seckillPrice;
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
    return "GoodsVo{"
        + "id='"
        + id
        + '\''
        + ", goodsName='"
        + goodsName
        + '\''
        + ", goodsTitle='"
        + goodsTitle
        + '\''
        + ", goodsImg='"
        + goodsImg
        + '\''
        + ", goodsDetail='"
        + goodsDetail
        + '\''
        + ", goodsPrice="
        + goodsPrice
        + ", goodsStock="
        + goodsStock
        + ", seckillPrice="
        + seckillPrice
        + ", stockCount="
        + stockCount
        + ", startDate="
        + startDate
        + ", endDate="
        + endDate
        + '}';
  }
}
