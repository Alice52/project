package cn.edu.ntu.seckill.aop;

import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
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

    log.error("target: {}", "sasa");
    log.error(
        "target: {}; method signature: {}; args: {};",
        JSONUtil.toJsonStr(target),
        JSONUtil.toJsonStr(mSignature),
        JSONUtil.toJsonStr(args));
  }
}
