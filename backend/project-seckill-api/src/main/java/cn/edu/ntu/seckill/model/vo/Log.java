package cn.edu.ntu.seckill.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author zack <br>
 * @create 2020-08-02 18:46 <br>
 * @project project-seckill <br>
 */
@Data
@ToString
public class Log {

  private String url;
  private String beanName;
  private String user;
  private String methodName;
  private String params;
  private String sessionId;
  private String uri;
  private long requestTime;
  private String remoteAddr;
  private String result;
}
