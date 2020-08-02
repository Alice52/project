package cn.edu.ntu.seckill.aop;

import cn.edu.ntu.seckill.model.vo.Log;
import cn.hutool.core.util.ObjectUtil;
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
@Slf4j
@Aspect
@Component
public class ControllerAspect {

  private ThreadLocal<Log> tlocal = new ThreadLocal<Log>();

  @Pointcut("execution (* cn.edu.ntu.seckill.controller.*.*(..))")
  public void requestLogAspect() {}

  @Before("requestLogAspect()")
  public void validateBefore(JoinPoint joinPoint) throws Exception {
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

      log.error("target: {}", JSONUtil.toJsonStr("ads"));

      log.error(
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
      optLog.setParams(params != null ? params.toString() : "");
      optLog.setRemoteAddr(remoteAddr);
      optLog.setSessionId(sessionId);
      optLog.setUri(uri);
      optLog.setRequestTime(beginTime);
      tlocal.set(optLog);

    } catch (Exception e) {
      log.error("***Operation request logging failed  doBefore()***", e);
    }
  }

  @AfterReturning(returning = "result", pointcut = "requestLogAspect()")
  public void doAfterReturning(Object result) {
    try {
      Log optLog = tlocal.get();
      if (ObjectUtil.isEmpty(result)) {
        log.error(" response result is null");
        return;
      }
      optLog.setResult(result.toString());
      long beginTime = optLog.getRequestTime();
      long requestTime = (System.currentTimeMillis() - beginTime) / 1000;
      optLog.setRequestTime(requestTime);

      log.error(
          "[exit] duration time: {}s, uri: {},  method name: {},  params: {}, parameters: {}",
          requestTime,
          optLog.getUri(),
          optLog.getMethodName(),
          optLog.getRequestTime(),
          optLog.getParams());
      tlocal.remove();
    } catch (Exception e) {
      log.error("***Operation request logging failed doAfterReturning()***", e);
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
