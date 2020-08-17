package cn.edu.ntu.seckill.aop;

import cn.edu.ntu.seckill.component.AppContext;
import cn.edu.ntu.seckill.constants.AppContextConstant;
import cn.edu.ntu.seckill.model.vo.LogVO;
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
import java.util.Map;

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
      Object[] params = joinPoint.getArgs();
      Map<String, String[]> parameters = request.getParameterMap();

      log.info(
          "[enter] uri: {}, remote-addr: {}, method name: {}#{}, method: {}, params: {}, parameters: {}",
          uri,
          remoteAddr,
          beanName,
          methodName,
          method,
          params,
          parameters);

      LogVO optLogVO = new LogVO();
      optLogVO.setBeanName(beanName);
      optLogVO.setMethodName(methodName);
      optLogVO.setParams(params);
      optLogVO.setRemoteAddr(remoteAddr);
      optLogVO.setSessionId(sessionId);
      optLogVO.setUri(uri);
      optLogVO.setRequestTime(beginTime);
      AppContext.upsertByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG, optLogVO);

    } catch (Exception e) {
      log.error("***Operation request logging failed  doBefore()***", e);
    }
  }

  @AfterReturning(returning = "result", pointcut = "requestLogAspect()")
  public void doAfterReturning(Object result) {
    try {
      LogVO optLogVO =
          AppContext.getByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG, LogVO.class);

      log.info(
          "[exit] result: {} duration time: {}ms, uri: {},  method name: {}#{}",
          result,
          System.currentTimeMillis() - optLogVO.getRequestTime(),
          optLogVO.getUri(),
          optLogVO.getBeanName(),
          optLogVO.getMethodName());
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
