package cn.edu.ntu.seckill.jdcloud.starter.service;

import cn.edu.ntu.seckill.jdcloud.starter.properties.UserProperties;
import cn.edu.ntu.seckill.jdcloud.starter.utils.ThirdPartyApiCall;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static java.util.Base64.getEncoder;

/**
 * <link>https://docs.jdcloud.com/cn/ias/oauth2-service</link>
 *
 * <p><link>https://oauth2.jdcloud.com/authorize?response_type=code&redirect_uri=http://101.132.45.28:8008/login/oauth-done&client_id=9511590896016473&state=eyJhcHBJZCI6Ijk2OTE1Nzc2NzY0MjgxNTYiLCJwcm92aWRlclR5cGUiOiJNaWNyb3NvZnQiLCJwcm92aWRlclVzZXJBbGlhc0lkRmllbGQiOiJ1c2VyUHJpbmNpcGFsTmFtZSIsInJlZGlyZWN0VXJsIjoiYUhSMGNDVXpRU1V5UmlVeVJtOWhkWFJvTWkxemRHRm5MbXBrWTJ4dmRXUXVZMjl0In0
 * </link>
 *
 * @author zack <br>
 * @create 2020-05-26 21:10 <br>
 * @project seckill-backend <br>
 */
@Service
public class LoginService {
  private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

  @Resource private RestTemplate restTemplate;
  @Resource private UserProperties userProperties;

  @Resource private ThirdPartyApiCall thirdPartyApiCall;

  /**
   * Get token by client id and secret and code got from controller; <br>
   * Jdcloud provided apis:
   *
   * @return
   */
  public ResponseEntity<JSONObject> jdClodOauthDone(@Valid @NotBlank String code) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE));
    String encodingValue = userProperties.getClientId() + ":" + userProperties.getClientSecret();
    headers.add("Authorization", "Basic " + getEncoder().encodeToString(encodingValue.getBytes()));
    String url = "https://oauth2.jdcloud.com/token?grant_type=authorization_code&code=" + code;
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    LOG.info(
        "start request: method: {}, url: {}, header: {}, body {}",
        HttpMethod.GET,
        url,
        httpEntity,
        null);

    ResponseEntity<JSONObject> response =
        restTemplate.exchange(url, HttpMethod.GET, httpEntity, JSONObject.class);

    LOG.info(
        "finish request: method: {}, url: {}, header: {}, body {}",
        HttpMethod.GET,
        url,
        response.getHeaders(),
        response.getBody());

    LOG.debug("raw response: {}", JSONUtil.toJsonStr(response));

    return response;
  }

  /**
   * Get user info from jd cloud api.
   *
   * @param accessToken
   * @return
   */
  public ResponseEntity<JSONObject> jdClodGetUser(String accessToken) {

    return thirdPartyApiCall.jdClodGetUser(accessToken);
  }
}
