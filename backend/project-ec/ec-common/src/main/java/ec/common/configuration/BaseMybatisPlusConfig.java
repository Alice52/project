package ec.common.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;

/**
 * @author zack <br>
 * @create 2020-10-11 19:33 <br>
 * @project project-ec <br>
 */
public abstract class BaseMybatisPlusConfig {
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // paginationInterceptor.setOverflow(false);
    paginationInterceptor.setLimit(1000);
    paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));

    return paginationInterceptor;
  }
}
