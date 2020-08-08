package cn.edu.ntu.seckill.model.po;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-08 14:21 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BasePO {
  private String id;

  @DateTimeFormat private LocalDateTime createdDate;

  /** This column can be modify in db, use trigger if changed. */
  @DateTimeFormat private LocalDateTime updatedDate;

  @DateTimeFormat private boolean isDeleted;
}
