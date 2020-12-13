package ec.common.response;

import cn.hutool.core.util.ObjectUtil;
import ec.common.utils.PageUtils;
import ec.common.utils.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zack <br>
 * @create 2020/12/12 <br>
 * @project project-ec <br>
 */
public interface ICustomResponseAdvice extends ResponseBodyAdvice {

  /**
   * Whether need handle[override] response.
   *
   * @param returnType
   * @param converterType
   * @return
   */
  @Override
  default boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  default Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    if (body instanceof PageUtils) {
      return R.ok().put("page", body);
    } else if (ObjectUtil.isNull(body)) {
      return R.ok();
    } else {
      return R.ok().put("data", body);
    }
  }
}
