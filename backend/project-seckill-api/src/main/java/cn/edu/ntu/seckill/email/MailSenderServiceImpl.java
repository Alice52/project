package cn.edu.ntu.seckill.email;

import cn.edu.ntu.seckill.component.EmailUtil;
import cn.edu.ntu.seckill.model.bo.EmailStruct;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
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

  @Resource private EmailUtil emailUtil;

  @Override
  public boolean sendSimpleMailMessage(String to, String subject, String content) {

    return emailUtil.send(
        new EmailStruct(to, subject, content),
        () -> {
          SimpleMailMessage message = new SimpleMailMessage();
          message.setTo(to);
          message.setSubject(subject);
          message.setText(content);
          return message;
        });
  }

  @Override
  public boolean sendMimeMessage(String to, String subject, String content) {

    return this.sendMimeMessage(to, subject, content, StrUtil.EMPTY);
  }

  @Override
  public boolean sendMimeMessage(String to, String subject, String content, String filePath) {

    return emailUtil.send(
        new EmailStruct(to, subject, content, filePath),
        () -> {
          MimeMessage message = null;
          try {
            message = emailUtil.getMailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            if (StrUtil.isNotEmpty(filePath)) {
              FileSystemResource file = new FileSystemResource(new File(filePath));
              String fileName = file.getFilename();
              helper.addAttachment(fileName, file);
            }

          } catch (MessagingException ex) {
            log.error("send email to {} error, subject is {} body is {}", to, subject, content);
          }

          return message;
        });
  }

  @Override
  public boolean sendMimeMessage(
      String to, String subject, String content, Map<String, String> rscIdMap) {

    return emailUtil.send(
        new EmailStruct(to, subject, content, rscIdMap),
        () -> {
          MimeMessage message = null;
          try {
            message = emailUtil.getMailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
              FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
              helper.addInline(entry.getKey(), file);
            }
          } catch (MessagingException ex) {
            log.error(
                "build message error with parameters: to[{}] subject[{}] content[{}] rscIdMap[{}]",
                to,
                subject,
                content,
                rscIdMap);
          }

          return message;
        });
  }
}
