package cn.edu.ntu.seckill.interceptor;

import cn.edu.ntu.seckill.component.AppContext;
import cn.edu.ntu.seckill.constants.AppContextConstant;
import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zack <br>
 * @create 2020-08-05 22:25 <br>
 * @project project-seckill <br>
 */
@Component
@Order(1)
public class RequestInterceptor extends HandlerInterceptorAdapter {

  public static final String REQUEST_ID_KEY = "request-id";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    MDC.put(REQUEST_ID_KEY, getRequestId(request));
    /** AppContext.upsertByKey(AppContextConstant.REQUEST_ID, IdUtil.fastSimpleUUID()); */
    return super.preHandle(request, response, handler);
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    response.addHeader(REQUEST_ID_KEY, MDC.get(REQUEST_ID_KEY));
    MDC.remove(REQUEST_ID_KEY);
  }

  private static String getRequestId(HttpServletRequest request) {
    String requestId;
    String parameterRequestId = request.getParameter(REQUEST_ID_KEY);
    String headerRequestId = request.getHeader(REQUEST_ID_KEY);
    if (parameterRequestId == null && headerRequestId == null) {
      requestId = IdUtil.fastUUID();
    } else {
      requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
    }
    return requestId;
  }
}
