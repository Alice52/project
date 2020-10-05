package ec.common.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zack <br>
 * @create 2020-10-05 15:35 <br>
 * @project project-ec <br>
 */
@Component
public class EcExceptionHandler implements HandlerExceptionResolver {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public ModelAndView resolveException(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    R r = new R();
    try {
      response.setContentType("application/json;charset=utf-8");
      response.setCharacterEncoding("utf-8");

      if (ex instanceof EcException) {
        r.put("code", ((EcException) ex).getCode());
        r.put("msg", ex.getMessage());
      } else if (ex instanceof DuplicateKeyException) {
        r = R.error("数据库中已存在该记录");
      } else {
        r = R.error();
      }

      logger.error(ex.getMessage(), ex);

      String json = JSON.toJSONString(r);
      response.getWriter().print(json);
    } catch (Exception e) {
      logger.error("ExceptionHandler throw exception", e);
    }
    return new ModelAndView();
  }
}
