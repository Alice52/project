package cn.edu.ntu.seckill.jdcloud.starter.annotation.descriptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zack <br>
 * @create 2020-05-26 21:51 <br>
 * @project seckill-backend <br>
 */
public class ValidateTokenDescriptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    // Get access token from request cookie and validate by call jdcloud api
    // If valid, response true, else throw exception, or false, this need check again

    return super.preHandle(request, response, handler);
  }
}
