package cn.edu.ntu.seckill.aop;

import cn.edu.ntu.seckill.component.AppContext;
import cn.edu.ntu.seckill.constants.CommonConstant;
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
 * // TODO: if debug level is set to debug, then will enable this aspect for detail log.
 *
 * @author zack <br>
 * @create 2020-07-26 23:48 <br>
 * @project project-seckill <br>
 */
public class ServiceAspect {}
