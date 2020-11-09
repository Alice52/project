package ec.product.converter;

import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2020-11-08 22:51 <br>
 * @project project-ec <br>
 */
@Component
public class BooleanStrFormat {
  public String toStr(Boolean isDisable) {
    if (isDisable) {
      return "Y";
    } else {
      return "N";
    }
  }

  public Boolean toBoolean(String str) {
    if (str.equals("Y")) {
      return true;
    } else {
      return false;
    }
  }
}
