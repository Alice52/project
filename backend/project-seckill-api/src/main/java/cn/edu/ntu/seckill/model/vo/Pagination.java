package cn.edu.ntu.seckill.model.vo;

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
public class Pagination {

    private Integer total;
    private Integer pageCount;
    private Integer currentPage;
    private Integer pageSize;

    public Pagination(Integer total, Integer pageCount, Integer currentPage, Integer pageSize) {
        this.total = total;
        this.pageCount = pageCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
