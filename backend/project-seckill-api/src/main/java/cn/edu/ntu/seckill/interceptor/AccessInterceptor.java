package cn.edu.ntu.seckill.interceptor;

import cn.edu.ntu.seckill.component.AppContext;
import cn.edu.ntu.seckill.constants.CommonConstant;
import cn.hutool.core.util.IdUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author zack <br>
 * @create 2020-08-05 22:25 <br>
 * @project project-seckill <br>
 */
@Component
@Order(1)
public class AccessInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    AppContext.initAppHolder(new HashMap<>(16));
    AppContext.upsertByKey(CommonConstant.REQUEST_ID, IdUtil.fastSimpleUUID());

    return super.preHandle(request, response, handler);
  }
}
