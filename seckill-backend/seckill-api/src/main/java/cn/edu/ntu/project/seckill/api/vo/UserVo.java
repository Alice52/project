package cn.edu.ntu.project.seckill.api.vo;

import cn.edu.ntu.project.seckill.api.annotation.ValidateMobile;

/**
 * @author zack <br>
 * @create 2020-05-26 22:40 <br>
 * @project seckill-backend <br>
 */
public class UserVo {

  @ValidateMobile private String mobile;

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
