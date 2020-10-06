package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.OrderItemRepository;
import ec.order.entity.OrderItemEntity;
import ec.order.service.OrderItemService;

@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemRepository, OrderItemEntity>
    implements OrderItemService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderItemEntity> page =
        this.page(
            new CommonQuery<OrderItemEntity>().getPage(params),
            new QueryWrapper<OrderItemEntity>());

    return new PageUtils(page);
  }
}
