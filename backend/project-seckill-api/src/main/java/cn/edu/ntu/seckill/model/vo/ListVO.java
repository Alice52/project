package cn.edu.ntu.seckill.model.vo;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2020-07-21 23:57 <br>
 * @project project-seckill <br>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ListVO<T> {
    private Pagination meta;
    private List<T> items;

    public ListVO(Pagination pagination, List<T> vos) {
        this.meta = pagination;
        this.items = vos;
    }
}
