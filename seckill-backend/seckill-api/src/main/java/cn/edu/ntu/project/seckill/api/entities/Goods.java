package cn.edu.ntu.project.seckill.api.entities;

import java.math.BigDecimal;

/**
 * @author zack <br>
 * @create 2020-06-01 23:12 <br>
 * @project seckill-backend <br>
 */
public class Goods {
  private Long id;
  private String goodsName;
  private String goodsTitle;
  private String goodsImg;
  private String goodsDetail;
  private BigDecimal goodsPrice;
  private Integer goodsStock;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  @Override
  public String toString() {
    return "Order{"
        + "id="
        + id
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
        + '}';
  }
}
