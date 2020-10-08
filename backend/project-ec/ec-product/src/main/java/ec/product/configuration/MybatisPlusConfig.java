package ec.product.configuration;

import ec.common.configuration.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zack <br>
 * @create 2020-10-11 19:37 <br>
 * @project project-ec <br>
 */
@Configuration
@MapperScan("ec.product.repository")
public class MybatisPlusConfig extends BaseMybatisPlusConfig {}
