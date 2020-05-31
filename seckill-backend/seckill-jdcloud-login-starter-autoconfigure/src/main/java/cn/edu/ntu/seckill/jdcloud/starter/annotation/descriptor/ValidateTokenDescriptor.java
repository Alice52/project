package cn.edu.ntu.seckill.jdcloud.starter.annotation.descriptor;

import cn.edu.ntu.project.seckill.common.constant.Constants;
import cn.edu.ntu.project.seckill.common.model.ErrorMessageEnum;
import cn.edu.ntu.project.seckill.common.model.ErrorResponse;
import cn.edu.ntu.seckill.jdcloud.starter.annotation.ValidateToken;
import cn.edu.ntu.seckill.jdcloud.starter.utils.ThirdPartyApiCall;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * @create 2020-05-26 21:51 <br>
 * @project seckill-backend <br>
 */
@Component
public class ValidateTokenDescriptor extends HandlerInterceptorAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(ValidateTokenDescriptor.class);

  @Resource private ThirdPartyApiCall thirdPartyApiCall;

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    if (handler instanceof HandlerMethod) {
      HandlerMethod hm = (HandlerMethod) handler;
      ValidateToken validateToken = hm.getMethodAnnotation(ValidateToken.class);

      if (validateToken == null) {
        return true;
      } else {
        String token = getCookieValue(request, Constants.ACCESS_TOKEN);

        if (validateAccessToken(token)) {
          return true;
        } else {
          render(response, ErrorMessageEnum.INVALID_TOKEN);
          return false;
        }
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
      errorResponse.setParameters(MapUtil.of("invalid token", "please login"));
      String jsonStr = JSONUtil.toJsonStr(errorResponse);
      out.write(jsonStr.getBytes("UTF-8"));
      out.flush();
      out.close();

    } catch (IOException e) {
      LOG.error("ValidateToken Annotation render to response failed");
    }
  }

  private boolean validateAccessToken(String accessToken) {

    ResponseEntity<JSONObject> entity = thirdPartyApiCall.jdClodGetUser(accessToken);

    return entity.getStatusCode().equals(HttpStatus.OK);
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
