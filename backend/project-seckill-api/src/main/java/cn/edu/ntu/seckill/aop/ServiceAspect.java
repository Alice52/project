package cn.edu.ntu.seckill.aop;

import cn.edu.ntu.seckill.component.AppContext;
import cn.edu.ntu.seckill.constants.AppContextConstant;
import cn.edu.ntu.seckill.model.vo.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * // TODO: if debug level is set to debug, then will enable this aspect for detail log.
 *
 * @author zack <br>
 * @create 2020-07-26 23:48 <br>
 * @project project-seckill <br>
 */
@Aspect
@Component
@Slf4j
public class ServiceAspect {
  private static final String ASPECT_PREFIX = "service:";

  @Pointcut("execution (* cn.edu.ntu.seckill.email.*.*(..))")
  public void requestLogAspect() {}

  @Before("requestLogAspect()")
  public void validateBefore(JoinPoint joinPoint) {
    try {
      if (log.isDebugEnabled()) {
        long beginTime = System.currentTimeMillis();
        String beanName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();

        Log optLog = new Log();
        optLog.setBeanName(beanName);
        optLog.setMethodName(methodName);
        optLog.setParams(params);
        optLog.setRequestTime(beginTime);

        log.info("[enter] beanName: {}, methodName: {}, params: {}", beanName, methodName, params);

        AppContext.upsertByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG, optLog);
      }
    } catch (Exception e) {
      log.error("***Operation service logging failed  doBefore()***", e);
    }
  }

  @AfterReturning(returning = "result", pointcut = "requestLogAspect()")
  public void doAfterReturning(Object result) {
    try {
      if (log.isDebugEnabled()) {
        Log optLog =
            AppContext.getByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG, Log.class);

        log.info(
            "[exit] result: {}, duration time: {}ms, method name: {}, params: {}",
            result,
            System.currentTimeMillis() - optLog.getRequestTime(),
            optLog.getMethodName(),
            optLog.getParams());
      }
    } catch (Exception e) {
      log.error("***Operation service logging failed doAfterReturning()***", e);
    } finally {
      AppContext.removeByKey(ASPECT_PREFIX, AppContextConstant.APP_CONTEXT_LOG);
    }
  }
}
