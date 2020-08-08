package cn.edu.ntu.seckill.plus.email;

import cn.edu.ntu.seckill.email.IMailSenderService;
import cn.hutool.core.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-08-05 20:59 <br>
 * @project project-seckill <br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {
  @Resource private IMailSenderService mailService;

  private static final String TO = "1252068782@qq.com";
  private static final String SUBJECT = "seckill validation code email";
  private static final String CONTENT = "validation code content";

  @Test
  public void sendSimpleMailMessage() {
    Assert.isTrue(mailService.sendSimpleMailMessage(TO, SUBJECT, CONTENT));
  }

  @Test
  public void sendHtmlMessage() {
    String htmlStr = "<h1>Test</h1>";
    Assert.isTrue(mailService.sendMimeMessage(TO, SUBJECT, htmlStr));
  }

  @Test
  public void sendAttachmentMessage() throws FileNotFoundException {
    File file = ResourceUtils.getFile("classpath:application.yml");
    String filePath = file.getAbsolutePath();
    Assert.isTrue(mailService.sendMimeMessage(TO, SUBJECT, CONTENT, filePath));
  }

  @Test
  public void sendPicMessage() throws FileNotFoundException {
    String htmlStr = "<html><body>图片1 <br> <img src=\'cid:pic1\'/> </body></html>";
    Map<String, String> rscIdMap = new HashMap<>(2);
    rscIdMap.put("pic1", ResourceUtils.getFile("classpath:static/14894-8.jpg").getAbsolutePath());
    Assert.isTrue(mailService.sendMimeMessage(TO, SUBJECT, htmlStr, rscIdMap));
  }
}
