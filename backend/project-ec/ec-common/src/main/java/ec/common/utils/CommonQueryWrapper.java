package ec.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author zack <br>
 * @create 2020-10-05 18:33 <br>
 * @project project-ec <br>
 */
public class CommonQueryWrapper<T> extends QueryWrapper<T> {

  public CommonQueryWrapper(T entity) {
    super(entity);
    this.eq("is_deleted", false);
  }

  public CommonQueryWrapper(T entity, String... columns) {
    super(entity, columns);
    this.eq("is_deleted", false);
  }

  public CommonQueryWrapper() {
    super();
    this.eq("is_deleted", false);
  }
}
