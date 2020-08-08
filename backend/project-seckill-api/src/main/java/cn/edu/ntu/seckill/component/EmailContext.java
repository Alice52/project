package cn.edu.ntu.seckill.component;

import cn.edu.ntu.seckill.model.bo.EmailStruct;
import cn.edu.ntu.seckill.utils.ValidatorUtils;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.function.Supplier;

/**
 * Supplier<R> supplier cannot compile error, I think this is strange.
 *
 * <p>// TODO: this need check and do research.
 *
 * @author zack <br>
 * @create 2020-08-06 21:46 <br>
 * @project project-seckill <br>
 */
@Component
@Slf4j
public class EmailContext<T extends EmailStruct, R extends Object> {

  @Resource private JavaMailSender mailSender;

  public JavaMailSender getMailSender() {
    return mailSender;
  }

  public void setMailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Value("${spring.mail.username}")
  private String sender;

  public boolean send(T t, Supplier<R> supplier) {

    // 1. parameter validation
    if (StrUtil.isBlank(t.getTo()) || !ValidatorUtils.validateEmail(t.getTo())) {
      log.error("email receiver invalid: {}", t.getTo());
      return false;
    }

    // 2. get build message
    R r = supplier.get();

    try {
      // 3. send message
      if (r instanceof SimpleMailMessage) {
        SimpleMailMessage message = (SimpleMailMessage) r;
        message.setFrom(sender);
        mailSender.send(message);
      } else if (r instanceof MimeMessage) {
        MimeMessage message = (MimeMessage) r;
        message.setFrom(sender);
        mailSender.send(message);
      } else {
        log.error(
            "send email error: body {} cause by unknown message type {}",
            t,
            r.getClass().getTypeName());
        return false;
      }

    } catch (MailException | MessagingException ex) {
      // 4. handle send exceptions
      log.error("send email error: body {} message {} cause by {}", t, r, ex);
      return false;
    }

    return true;
  }
}
