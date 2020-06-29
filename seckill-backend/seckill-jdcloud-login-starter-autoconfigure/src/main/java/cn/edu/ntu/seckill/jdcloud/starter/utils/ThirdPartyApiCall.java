package cn.edu.ntu.seckill.jdcloud.starter.utils;

import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-05-31 14:44 <br>
 * @project seckill-backend <br>
 */
@Component
public class ThirdPartyApiCall {

  private static final Logger LOG = LoggerFactory.getLogger(ThirdPartyApiCall.class);

  @Resource private RestTemplate restTemplate;

  public ResponseEntity<JSONObject> jdClodGetUser(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE));
    headers.add("Authorization", "Bearer " + accessToken);
    String url = "https://oauth2.jdcloud.com/userinfo";
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
        "finish request: method: {}, url: {}, header: {}, status: {}, body {}",
        HttpMethod.GET,
        url,
        response.getHeaders(),
        response.getStatusCode(),
        response.getBody());

    return response;
  }
}
