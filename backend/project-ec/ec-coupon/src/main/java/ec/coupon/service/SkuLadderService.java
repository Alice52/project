package ec.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:06:03
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Create sku ladder info.
   *
   * @param entity
   */
  void saveSkuLadder(SkuLadderEntity entity);
}
