package cn.edu.ntu.project.seckill.api.annotation.descriptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zack <br>
 * @create 2020-05-26 22:21 <br>
 * @project seckill-backend <br>
 */
@Component
public class AccessLimitDescriptor extends HandlerInterceptorAdapter {

  /**
   * If this method return false, so this request status will also be 200, but body is null? <br>
   * I think this is strange. //TODO: need to check the usage of HandlerInterceptorAdapter
   *
   * @param request
   * @param response
   * @param handler
   * @return
   * @throws Exception
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    // limit, else throw exception
    return true;
  }
}
