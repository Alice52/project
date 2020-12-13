package ec.ware.repository;

import ec.ware.entity.PurchaseDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:43:08
 */
@Mapper
public interface PurchaseDetailRepository extends BaseMapper<PurchaseDetailEntity> {}
