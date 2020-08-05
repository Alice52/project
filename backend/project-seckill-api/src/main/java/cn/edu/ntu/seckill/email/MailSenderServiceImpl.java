package cn.edu.ntu.seckill.email;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-08-05 19:54 <br>
 * @project project-seckill <br>
 */
@Slf4j
@Service
public class MailSenderServiceImpl implements IMailSenderService {

  @Resource private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String SENDER;

  @Override
  public boolean sendSimpleMailMessage(String to, String subject, String content) {
    if (StrUtil.isBlank(to)) {
      return false;
    }

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(SENDER);
    message.setTo(to);
    message.setSubject(subject);
    message.setText(content);

    try {
      mailSender.send(message);
    } catch (MailException ex) {
      log.error(
          "send email from {} to {} error, subject is {} body is {} message {} cause by {}",
          SENDER,
          to,
          subject,
          content,
          message,
          ex);

      return false;
    }

    return true;
  }

  @Override
  public boolean sendMimeMessage(String to, String subject, String content) {

    return this.sendMimeMessage(to, subject, content, StrUtil.EMPTY);
  }

  @Override
  public boolean sendMimeMessage(String to, String subject, String content, String filePath) {

    if (StrUtil.isBlank(to)) {
      return false;
    }

    MimeMessage message = mailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = buildBasicMimeMessage(message, to, subject, content);
      if (StrUtil.isNotEmpty(filePath)) {
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);
      }
    } catch (MessagingException e) {
      log.error("send email to {} error, subject is {} body is {}", to, subject, content);
      return false;
    }

    return send(to, subject, content, message);
  }

  @Override
  public boolean sendMimeMessage(
      String to, String subject, String content, Map<String, String> rscIdMap) {
    MimeMessage message = mailSender.createMimeMessage();
    if (StrUtil.isBlank(to)) {
      return false;
    }

    try {
      MimeMessageHelper helper = buildBasicMimeMessage(message, to, subject, content);
      for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
        FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
        helper.addInline(entry.getKey(), file);
      }
    } catch (MessagingException e) {
      log.error(
          "build message error with parameters: to[{}] subject[{}] content[{}] rscIdMap[{}]",
          to,
          subject,
          content,
          rscIdMap);
      return false;
    }

    return send(to, subject, content, message);
  }

  private MimeMessageHelper buildBasicMimeMessage(
      MimeMessage message, String to, String subject, String content) throws MessagingException {

    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(SENDER);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(content, true);

    return helper;
  }

  private boolean send(String to, String subject, String content, MimeMessage message) {
    try {
      mailSender.send(message);
      return true;
    } catch (MailException ex) {
      log.error("send email to {} error, subject is {} body is {}", to, subject, content);
      return false;
    }
  }
}
