package cn.edu.ntu.seckill.model.po;

import lombok.*;

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
  private boolean isDeleted;
  private LocalDateTime createdDate;
  /** This column can be modify in db, use trigger if changed. */
  private LocalDateTime updatedDate;
}
