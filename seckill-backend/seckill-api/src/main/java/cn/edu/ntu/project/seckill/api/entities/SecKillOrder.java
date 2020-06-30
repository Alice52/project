package cn.edu.ntu.project.seckill.api.entities;

/**
 * @author zack <br>
 * @create 2020-06-01 23:23 <br>
 * @project seckill-backend <br>
 */
public class SecKillOrder {
  private String id;
  private String userId;
  private String orderId;
  private String goodsId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(String goodsId) {
    this.goodsId = goodsId;
  }

  @Override
  public String toString() {
    return "SeckillOrder{"
        + "id='"
        + id
        + '\''
        + ", userId='"
        + userId
        + '\''
        + ", orderId='"
        + orderId
        + '\''
        + ", goodsId='"
        + goodsId
        + '\''
        + '}';
  }
}
