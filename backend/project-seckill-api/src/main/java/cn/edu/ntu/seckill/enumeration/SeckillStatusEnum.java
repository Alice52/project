package cn.edu.ntu.seckill.enumeration;

/**
 * @author zack <br>
 * @create 2020-08-18 20:46 <br>
 * @project project-seckill <br>
 */
public enum SeckillStatusEnum {
  NOT_STARTED("not start"),
  ON_GOING("ongoing"),
  FINISHED("finished");
  private String detail;

  SeckillStatusEnum() {}

  SeckillStatusEnum(String detail) {
    this.detail = detail;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
}
