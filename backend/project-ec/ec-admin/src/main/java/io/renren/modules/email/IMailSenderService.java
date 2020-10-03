package io.renren.modules.email;

import java.util.Map;

/**
 * @author zack <br/>
 * @create 2020-10-03 21:54 <br/>
 * @project project-ec <br/>
 */
public interface IMailSenderService {
    /**
     * Send simple email.
     *
     * @param to
     * @param subject
     * @param content
     * @return success
     */
    boolean sendSimpleMailMessage(String to, String subject, String content);

    /**
     * Send mine email with html content.
     *
     * @param to
     * @param subject
     * @param htmlContent
     * @return success
     */
    boolean sendMimeMessage(String to, String subject, String htmlContent);

    /**
     * Send mine email with attachment.
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @return success
     */
    boolean sendMimeMessage(String to, String subject, String content, String filePath);

    /**
     * Send mine email with static resource.
     *
     * @param to
     * @param subject
     * @param content
     * @param rscIdMap
     * @return success
     */
    boolean sendMimeMessage(String to, String subject, String content, Map<String, String> rscIdMap);
}
