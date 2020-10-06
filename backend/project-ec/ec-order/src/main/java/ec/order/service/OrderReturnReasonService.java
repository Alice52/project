package ec.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
