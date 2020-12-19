package ec.ware.repository;

import ec.ware.model.entity.WareOrderTaskDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Mapper
public interface WareOrderTaskDetailRepository extends BaseMapper<WareOrderTaskDetailEntity> {}
