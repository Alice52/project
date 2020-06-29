package cn.edu.ntu.project.seckill.api.configuration;

import cn.edu.ntu.project.seckill.api.component.UserContext;
import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author zack <br>
 * @create 2020-06-29 21:08 <br>
 * @project seckill-backend <br>
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    Class<?> clazz = parameter.getParameterType();
    return clazz == SeckillUser.class;
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {
    return UserContext.getUser();
  }
}
