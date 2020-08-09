package cn.edu.ntu.seckill.configuarion;

import cn.edu.ntu.seckill.exception.UserException;
import cn.edu.ntu.seckill.model.bo.UserBO;
import cn.edu.ntu.seckill.model.po.UserPO;
import cn.edu.ntu.seckill.redis.RedisUserKeyEnum;
import cn.edu.ntu.seckill.utils.RedisKeyUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-08-09 17:01 <br>
 * @project project-seckill <br>
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

  @Resource RedisTemplate redisTemplate;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    Class<?> clazz = parameter.getParameterType();
    return clazz == UserBO.class;
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {

    String[] headerValues = webRequest.getHeaderValues("token");
    if (!ObjectUtil.isNotNull(headerValues)) {
      throw new UserException().new InvalidTokenException();
    }

    String token = headerValues[0];
    Object object =
        redisTemplate
            .opsForValue()
            .get(RedisKeyUtils.buildKey(RedisUserKeyEnum.USER_TOKEN, token));

    if (!(object instanceof UserPO)) {
      throw new UserException().new InvalidTokenException();
    }

    UserPO userPO = (UserPO) object;
    UserBO userBO = new UserBO();
    BeanUtil.copyProperties(userPO, userBO);
    userBO.setToken(token);

    return userBO;
  }
}
