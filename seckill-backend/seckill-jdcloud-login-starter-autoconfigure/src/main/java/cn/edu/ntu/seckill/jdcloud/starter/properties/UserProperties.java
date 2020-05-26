package cn.edu.ntu.seckill.jdcloud.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zack <br>
 * @create 2020-05-26 21:11 <br>
 * @project seckill-backend <br>
 */
@EnableConfigurationProperties(UserProperties.class)
@ConfigurationProperties(prefix = "jdcloud")
public class UserProperties {

  private String username;
  private String password;
  private String pin;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public UserProperties() {}

  public UserProperties(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public UserProperties(String username, String password, String pin) {
    this.username = username;
    this.password = password;
    this.pin = pin;
  }
}
