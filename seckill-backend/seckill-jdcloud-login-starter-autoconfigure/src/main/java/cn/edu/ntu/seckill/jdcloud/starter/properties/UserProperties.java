package cn.edu.ntu.seckill.jdcloud.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zack <br>
 * @create 2020-05-26 21:11 <br>
 * @project seckill-backend <br>
 */
@ConfigurationProperties(prefix = "jdcloud")
public class UserProperties {

  private String clientId;
  private String clientSecret;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public UserProperties() {}

  public UserProperties(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }
}
