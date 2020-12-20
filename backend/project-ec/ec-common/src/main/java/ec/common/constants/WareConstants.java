package ec.common.constants;

/**
 * @author zack <br>
 * @create 2020-11-10 23:15 <br>
 * @project project-ec <br>
 */
public class WareConstants {

  public enum PurchaseStatusEnum {
    CREATED(0, "new created"),
    ASSIGNED(1, "allocated"),
    RECEIVED(2, "received"),
    FINISHED(3, "finished"),
    ABERRANT(4, "aberrant");

    private int code;
    private String msg;

    PurchaseStatusEnum(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public int getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }
  }

  public enum PurchaseDetailStatusEnum {
    CREATED(0, "created"),
    ASSIGNED(1, "allocated"),
    BUYING(2, "buying"),
    FINISHED(3, "finished"),
    FAILED(4, "aberrant");

    private int code;
    private String msg;

    PurchaseDetailStatusEnum(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public int getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }
  }
}
