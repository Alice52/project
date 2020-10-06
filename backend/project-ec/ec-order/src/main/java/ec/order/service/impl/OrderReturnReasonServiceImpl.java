package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.OrderReturnReasonRepository;
import ec.order.entity.OrderReturnReasonEntity;
import ec.order.service.OrderReturnReasonService;

@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl
    extends ServiceImpl<OrderReturnReasonRepository, OrderReturnReasonEntity>
    implements OrderReturnReasonService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderReturnReasonEntity> page =
        this.page(
            new CommonQuery<OrderReturnReasonEntity>().getPage(params),
            new QueryWrapper<OrderReturnReasonEntity>());

    return new PageUtils(page);
  }
}
