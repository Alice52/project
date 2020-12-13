package ec.common.error.handler;

import ec.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020-10-10 12:44 <br>
 * @project project-ec <br>
 */
@Slf4j
public abstract class BaseDefaultExceptionHandler {

  /**
   * This exception handler is handle <code>Assert.xx</code> methods.
   *
   * @param e
   * @param request
   * @return R
   */
  @ExceptionHandler({IllegalArgumentException.class})
  public R handleException(IllegalArgumentException e, HttpServletRequest request) {
    return R.error(400, e.getMessage());
  }

  @ExceptionHandler({Exception.class})
  public R handleException(Exception e, HttpServletRequest request) {

    e.printStackTrace();

    return R.error(500, e.getMessage()).put("detail", e.getStackTrace());
  }
}
