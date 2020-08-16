package cn.edu.ntu.seckill.utils;

import cn.edu.ntu.seckill.model.vo.Pagination;
import cn.hutool.core.util.PageUtil;

import java.util.Optional;

/**
 * @author zack <br>
 * @create 2020-08-16 22:36 <br>
 * @project project-seckill <br>
 */
public final class PaginationUtils {
  public static Pagination buildPagination(Integer total, Integer pageSize, Integer currentPage) {
    pageSize = Optional.ofNullable(pageSize).orElse(20);
    currentPage = Optional.ofNullable(currentPage).orElse(1);
    Integer pageCount = PageUtil.totalPage(total, pageSize);

    if (currentPage > pageCount) {
      currentPage = 1;
    }

    return new Pagination(total, pageCount, currentPage, pageSize);
  }
}
