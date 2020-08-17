package cn.edu.ntu.seckill.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zack <br>
 * @create 2020-08-02 18:46 <br>
 * @project project-seckill <br>
 */
@Data
@ToString
@EqualsAndHashCode
public class LogVO {

  private String url;
  private String beanName;
  private String user;
  private String methodName;
  private Object params;
  private String sessionId;
  private String uri;
  private long requestTime;
  private String remoteAddr;
  private String result;
}
