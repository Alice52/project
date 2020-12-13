package ec.common.swagger;

import lombok.Data;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Data
public abstract class AbstractSwaggerProperties {
  private Boolean enable;
  private String applicationName;
  private String applicationVersion;
  private String applicationDescription;
  private String tryHost;
}
