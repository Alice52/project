package ec.thirdparty.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import ec.thirdparty.service.IOssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-10-11 11:28 <br>
 * @project project-ec <br>
 */
@Service
public class OssService implements IOssService {

  @Resource private OSS ossClient;

  @Value("${spring.cloud.alicloud.access-key}")
  private String accessId;

  @Value("${spring.cloud.alicloud.oss.bucket-name}")
  private String bucketName;

  @Value("${spring.cloud.alicloud.oss.endpoint}")
  private String endpoint;

  @Override
  public String upload(String name, InputStream in) {

    ossClient.putObject(bucketName, name, in);
    ossClient.shutdown();

    return "https://" + bucketName + StrUtil.SLASH + endpoint + StrUtil.SLASH + name;
  }

    @Override
    public Map<String, String> signature() throws UnsupportedEncodingException {
        String host = "https://" + bucketName + "." + endpoint;
        String dir = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

        long expireEndTime = System.currentTimeMillis() + 30 * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", accessId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        return respMap;
    }
}
