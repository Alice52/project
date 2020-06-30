package cn.edu.ntu.project.seckill.api.mq;

import cn.edu.ntu.project.seckill.api.entities.SeckillUser;

/**
 * @author zack <br>
 * @create 2020-06-30 21:39 <br>
 * @project seckill-backend <br>
 */
public class SecKillMessage {
  private SeckillUser user;
  private String goodsId;

  public SeckillUser getUser() {
    return user;
  }

  public void setUser(SeckillUser user) {
    this.user = user;
  }

  public String getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(String goodsId) {
    this.goodsId = goodsId;
  }
}
