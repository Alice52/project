package cn.edu.ntu.seckill.model.vo;

import lombok.*;

/**
 * @author zack <br>
 * @create 2020-08-02 18:46 <br>
 * @project project-seckill <br>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pagination {

  private Integer total;
  private Integer pageCount;
  private Integer currentPage;
  private Integer pageSize;
}
