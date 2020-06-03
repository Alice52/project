package cn.edu.ntu.project.seckill.api.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zack <br>
 * @create 2020-05-17 13:13 <br>
 * @project seckill-backend <br>
 */
public class Order {
  private Long id;
  private Long userId;
  private Long goodsId;
  private Long deliveryAddrId;
  private String goodsName;
  private Integer goodsCount;
  private BigDecimal goodsPrice;
  private int orderChannel;
  private int orderStatus;
  private LocalDateTime createAt;
  private LocalDateTime payAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  public Long getDeliveryAddrId() {
    return deliveryAddrId;
  }

  public void setDeliveryAddrId(Long deliveryAddrId) {
    this.deliveryAddrId = deliveryAddrId;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public Integer getGoodsCount() {
    return goodsCount;
  }

  public void setGoodsCount(Integer goodsCount) {
    this.goodsCount = goodsCount;
  }

  public BigDecimal getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(BigDecimal goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

  public int getOrderChannel() {
    return orderChannel;
  }

  public void setOrderChannel(int orderChannel) {
    this.orderChannel = orderChannel;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public LocalDateTime getPayAt() {
    return payAt;
  }

  public void setPayAt(LocalDateTime payAt) {
    this.payAt = payAt;
  }

  @Override
  public String toString() {
    return "Order{"
        + "id="
        + id
        + ", userId="
        + userId
        + ", goodsId="
        + goodsId
        + ", deliveryAddrId="
        + deliveryAddrId
        + ", goodsName='"
        + goodsName
        + '\''
        + ", goodsCount="
        + goodsCount
        + ", goodsPrice="
        + goodsPrice
        + ", orderChannel="
        + orderChannel
        + ", orderStatus="
        + orderStatus
        + ", createAt="
        + createAt
        + ", payAt="
        + payAt
        + '}';
  }
}
