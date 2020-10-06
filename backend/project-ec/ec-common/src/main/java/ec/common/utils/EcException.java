package ec.common.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zack <br>
 * @create 2020-10-05 18:24 <br>
 * @project project-ec <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EcException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String msg;
  private int code = 500;

  public EcException() {}

  public EcException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public EcException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public EcException(String msg, int code) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public EcException(String msg, int code, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }
}
