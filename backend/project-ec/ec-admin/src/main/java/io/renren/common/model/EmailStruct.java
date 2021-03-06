package io.renren.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-10-03 21:56 <br>
 * @project project-ec <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailStruct {
  public String to;
  public String subject;
  public String content;
  public String filePath;
  public Map<String, String> rscIdMap;

  public EmailStruct(String to, String subject, String content) {
    this.to = to;
    this.subject = subject;
    this.content = content;
  }

  public EmailStruct(String to, String subject, String content, String filePath) {
    this.to = to;
    this.subject = subject;
    this.content = content;
    this.filePath = filePath;
  }

  public EmailStruct(String to, String subject, String content, Map<String, String> rscIdMap) {
    this.to = to;
    this.subject = subject;
    this.content = content;
    this.rscIdMap = rscIdMap;
  }
}
