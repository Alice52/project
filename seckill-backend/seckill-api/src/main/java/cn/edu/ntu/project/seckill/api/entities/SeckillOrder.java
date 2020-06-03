package cn.edu.ntu.project.seckill.api.entities;

/**
 * @author zack <br>
 * @create 2020-06-01 23:23 <br>
 * @project seckill-backend <br>
 */
public class SeckillOrder {
  private Long id;
  private Long userId;
  private Long orderId;
  private Long goodsId;

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

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  @Override
  public String toString() {
    return "SeckillOrder{"
        + "id="
        + id
        + ", userId="
        + userId
        + ", orderId="
        + orderId
        + ", goodsId="
        + goodsId
        + '}';
  }
}
