package cn.edu.ntu.project.seckill.api.annotation.descriptor;

import cn.edu.ntu.project.seckill.api.annotation.AccessLimit;
import cn.edu.ntu.project.seckill.api.component.UserContext;
import cn.edu.ntu.project.seckill.api.configuration.RedisAccessKey;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.exception.AccessLimitException;
import cn.edu.ntu.project.seckill.api.exception.SecKillException;
import cn.edu.ntu.project.seckill.api.exception.UserException;
import cn.edu.ntu.project.seckill.api.service.UserService;
import cn.edu.ntu.project.seckill.common.constant.Constants;
import cn.edu.ntu.project.seckill.common.model.ErrorMessageEnum;
import cn.edu.ntu.project.seckill.common.model.ErrorResponse;
import cn.edu.ntu.seckill.redis.starter.autoconfigure.service.RedisService;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zack <br>
 * @create 2020-05-26 22:21 <br>
 * @project seckill-backend <br>
 */
@Slf4j
@Component
public class AccessLimitDescriptor extends HandlerInterceptorAdapter {

  @Resource private UserService userService;
  @Resource private RedisService redisService;

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

    // limit, else throw exception or render data to response
    if (handler instanceof HandlerMethod) {
      SeckillUser user = this.getUser(request, response);
      UserContext.setUser(user);
      HandlerMethod hm = (HandlerMethod) handler;
      AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
      if (accessLimit == null) {
        return true;
      }

      int seconds = accessLimit.seconds();
      int maxCount = accessLimit.maxCount();
      boolean needLogin = accessLimit.needLogin();
      String key = request.getRequestURI();
      if (needLogin) {
        if (user == null) {
          throw new UserException().new UserLoginException();
        }
        key += "_" + user.getNickname();
      } else {
        // do nothing
      }
      RedisAccessKey ak = RedisAccessKey.withExpire(seconds);
      Integer count = redisService.get(ak, key, Integer.class);
      if (count == null) {
        redisService.set(ak, key, new Integer(1));
      } else if (count < maxCount) {
        redisService.incr(ak, key);
      } else {
        throw new AccessLimitException();
        // render(response, ErrorMessageEnum.ACCESS_LIMIT);
        //        return false;
      }
    }
    return true;
  }

  private void render(HttpServletResponse response, ErrorMessageEnum errorMessageEnum) {
    response.setContentType("application/json;charset=UTF-8");
    OutputStream out = null;
    try {
      out = response.getOutputStream();
      ErrorResponse errorResponse = ErrorResponse.error(errorMessageEnum);
      errorResponse.setParameters(MapUtil.of("access limit", "please wait to try again"));
      String jsonStr = JSONUtil.toJsonStr(errorResponse);
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      out.write(jsonStr.getBytes("UTF-8"));
      out.flush();
      out.close();

    } catch (IOException e) {
      log.error("AccessLimit Annotation render to response failed");
    }
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
