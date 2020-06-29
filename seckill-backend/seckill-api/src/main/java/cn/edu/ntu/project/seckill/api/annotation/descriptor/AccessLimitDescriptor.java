package cn.edu.ntu.project.seckill.api.annotation.descriptor;

import cn.edu.ntu.project.seckill.api.component.UserContext;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.service.UserService;
import cn.edu.ntu.project.seckill.common.constant.Constants;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zack <br>
 * @create 2020-05-26 22:21 <br>
 * @project seckill-backend <br>
 */
@Component
public class AccessLimitDescriptor extends HandlerInterceptorAdapter {

  @Resource private UserService userService;
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
    SeckillUser user = this.getUser(request, response);
    UserContext.setUser(user);

    return true;
  }

  private SeckillUser getUser(HttpServletRequest request, HttpServletResponse response) {
    String token = this.getCookieValue(request, Constants.ACCESS_TOKEN);
    if (StrUtil.isBlank(token)) {
      return null;
    }

    return userService.getByToken(response, token);
  }

  private String getCookieValue(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length <= 0) {
      return null;
    }
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(cookieName)) {
        return cookie.getValue();
      }
    }
    return null;
  }
}
