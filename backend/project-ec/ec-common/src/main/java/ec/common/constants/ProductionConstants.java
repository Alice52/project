package ec.common.constants;

/**
 * @author zack <br>
 * @create 2020-11-10 23:15 <br>
 * @project project-ec <br>
 */
public class ProductionConstants {

  public enum AttrEnum {
    ATTR_TYPE_SALE(0, "sale prop"),
    ATTR_TYPE_BASE(1, "base prop");

    private int code;
    private String msg;

    AttrEnum(int code, String msg) {
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
