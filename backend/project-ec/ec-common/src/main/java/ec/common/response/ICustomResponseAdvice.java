package ec.common.response;

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

  String[] ignores = new String[] {"/swagger-resources", "/v2/api-docs"};

  default boolean ignoring(String uri) {
    for (String string : ignores) {
      if (uri.contains(string)) {
        return true;
      }
    }
    return false;
  }

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

  /**
   * This method will worked when {@link ICustomResponseAdvice#supports(MethodParameter, Class) } is
   * return true. <br>
   * It will wrap response by specified logic. <br>
   *
   * @param body
   * @param returnType
   * @param selectedContentType
   * @param selectedConverterType
   * @param request
   * @param response
   * @return Object real response
   */
  @Override
  default Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    if (this.ignoring(request.getURI().toString()) || body instanceof R) {
      return body;
    }

    if (body instanceof PageUtils) {
      return R.ok().put("page", body);
    } else {
      return R.ok().put("data", body);
    }
  }
}
