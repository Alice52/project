package cn.edu.ntu.seckill.exception;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

/**
 * @author zack <br>
 * @create 2020-08-02 10:57 <br>
 * @project validation <br>
 */
public class ListValidException extends RuntimeException {

  private Map<Integer, Set<ConstraintViolation<Object>>> errors;

  public ListValidException(Map<Integer, Set<ConstraintViolation<Object>>> errors) {
    this.errors = errors;
  }

  public Map<Integer, Set<ConstraintViolation<Object>>> getErrors() {
    return errors;
  }

  public void setErrors(Map<Integer, Set<ConstraintViolation<Object>>> errors) {
    this.errors = errors;
  }
}
