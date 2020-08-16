package cn.edu.ntu.seckill.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zack <br>
 * @create 2020-08-02 18:46 <br>
 * @project project-seckill <br>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {

  private Integer total;
  private Integer pageCount;
  private Integer currentPage;
  private Integer pageSize;
}
