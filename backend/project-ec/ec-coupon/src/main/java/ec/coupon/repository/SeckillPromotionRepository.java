package ec.coupon.repository;

import ec.coupon.entity.SeckillPromotionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀活动
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:06:03
 */
@Mapper
public interface SeckillPromotionRepository extends BaseMapper<SeckillPromotionEntity> {}
