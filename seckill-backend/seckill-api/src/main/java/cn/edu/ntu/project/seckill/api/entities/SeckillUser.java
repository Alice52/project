package cn.edu.ntu.project.seckill.api.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-06-01 23:23 <br>
 * @project seckill-backend <br>
 */
public class SeckillUser {
  private String id;

  /** @Column is jpa or mybatis plus; in mybatis can use result map to handle this issue. */
  @NotNull private String nickname;

  @NotBlank private String password;
  private String salt;
  private LocalDateTime registerDate;

  public SeckillUser(String name) {
    this.nickname = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(LocalDateTime registerDate) {
    this.registerDate = registerDate;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Override
  public String toString() {
    return "SeckillUser{"
        + "id="
        + id
        + ", nickname='"
        + nickname
        + '\''
        + ", password='"
        + password
        + '\''
        + ", salt='"
        + salt
        + '\''
        + ", RegisterDate="
        + registerDate
        + '}';
  }
}
