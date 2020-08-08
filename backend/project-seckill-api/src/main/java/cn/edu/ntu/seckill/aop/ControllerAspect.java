package cn.edu.ntu.seckill.aop;

import cn.edu.ntu.seckill.component.AppContext;
import cn.edu.ntu.seckill.constants.AppContextConstant;
import cn.edu.ntu.seckill.model.vo.Log;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020-07-26 23:48 <br>
 * @project project-seckill <br>
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {
  private static final String ASPECT_PREFIX = "controller:";

  @Pointcut("execution (* cn.edu.ntu.seckill.controller.*.*(..))")
  public void requestLogAspect() {}

  @Before("requestLogAspect()")
  public void validateBefore(JoinPoint joinPoint) {
    try {
      long beginTime = System.currentTimeMillis();
      ServletRequestAttributes attributes =
          (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletRequest request = attributes.getRequest();
      String beanName = joinPoint.getSignature().getDeclaringTypeName();
      String methodName = joinPoint.getSignature().getName();
      String uri = request.getRequestURI();
      String remoteAddr = getIpAddr(request);
      String sessionId = request.getSession().getId();
      String method = request.getMethod();
      Object[] paramsArray = joinPoint.getArgs();
      String params = JSONUtil.toJsonStr(paramsArray);
      String parameters = JSONUtil.toJsonStr(request.getParameterMap());

      log.info(
          "[enter] uri: {}, beanName: {}, remoteAddr: {}, methodName: {}, method: {}, params: {}, parameters: {}",
          uri,
          beanName,
          remoteAddr,
          methodName,
          method,
          params,
          parameters);

      Log optLog = new Log();
      optLog.setBeanName(beanName);
      optLog.setMethodName(methodName);
      optLog.setParams(params != null ? params : "");
      optLog.setRemoteAddr(remoteAddr);
      optLog.setSessionId(sessionId);
      optLog.setUri(uri);
      optLog.setRequestTime(beginTime);
      AppContext.upsertByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG, optLog);

    } catch (Exception e) {
      log.error("***Operation request logging failed  doBefore()***", e);
    }
  }

  @AfterReturning(returning = "result", pointcut = "requestLogAspect()")
  public void doAfterReturning(Object result) {
    try {
      Log optLog =
          AppContext.getByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG, Log.class);

      log.info(
          "[exit] result: {} duration time: {}ms, uri: {},  method name: {},  params: {}, parameters: {}",
          result,
          System.currentTimeMillis() - optLog.getRequestTime(),
          optLog.getUri(),
          optLog.getMethodName(),
          optLog.getRequestTime(),
          optLog.getParams());
    } catch (Exception e) {
      log.error("***Operation request logging failed doAfterReturning()***", e);
    } finally {
      AppContext.removeByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG);
    }
  }

  /**
   * Gets the IP address of the login user's remote host
   *
   * @param request
   * @return
   */
  private static String getIpAddr(HttpServletRequest request) {
    final String UNKNOWN = "unknown";

    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
