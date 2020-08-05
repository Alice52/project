package cn.edu.ntu.seckill.aop;

import cn.edu.ntu.seckill.component.AppContext;
import cn.edu.ntu.seckill.constants.AppContextConstant;
import cn.edu.ntu.seckill.constants.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2020-07-26 23:48 <br>
 * @project project-seckill <br>
 */
@Slf4j
@Aspect
@Component
public class ExceptionAspect {

  @Pointcut("execution (* cn.edu.ntu.seckill.handler.*.*(..))")
  public void exceptionLogAspect() {}

  @Before("exceptionLogAspect()")
  public void validateBefore(JoinPoint joinPoint) throws Exception {
    Signature signature = joinPoint.getSignature();
    MethodSignature mSignature = (MethodSignature) signature;
    Object[] args = joinPoint.getArgs();
    Object target = joinPoint.getTarget();

    log.error(
        "[enter] requestId: {} target: {}; method signature: {}; args: {};",
        AppContext.getByKey(CommonConstant.REQUEST_ID, String.class),
        target,
        mSignature,
        args);

    AppContext.upsertByKey(AppContextConstant.APP_CONTEXT_EXCEPTION, System.currentTimeMillis());
  }

  @AfterReturning(returning = "result", pointcut = "exceptionLogAspect()")
  public void doAfterReturning(Object result) {

    log.error(
        "[exit] requestId: {} duration: {}, result: {}",
        AppContext.getByKey(CommonConstant.REQUEST_ID, String.class),
        (System.currentTimeMillis()
                - AppContext.getByKey(AppContextConstant.APP_CONTEXT_EXCEPTION, Long.class))
            / 1000,
        result);

    AppContext.removeByKey(AppContextConstant.APP_CONTEXT_EXCEPTION);
  }
}
