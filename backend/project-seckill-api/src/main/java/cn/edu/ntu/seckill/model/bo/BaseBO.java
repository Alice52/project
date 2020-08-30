package cn.edu.ntu.seckill.model.bo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-16 23:05 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseBO {
  private String id;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private Boolean isDeleted;

  public BaseBO(String id) {
    this.id = id;
  }
}
