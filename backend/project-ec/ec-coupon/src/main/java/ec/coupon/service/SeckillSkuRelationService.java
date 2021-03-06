package ec.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:06:03
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
