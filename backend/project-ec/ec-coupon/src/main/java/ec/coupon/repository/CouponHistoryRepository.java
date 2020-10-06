package ec.coupon.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ec.coupon.entity.CouponHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:06:03
 */
@Mapper
public interface CouponHistoryRepository extends BaseMapper<CouponHistoryEntity> {}
