package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.OrderRepository;
import ec.order.entity.OrderEntity;
import ec.order.service.OrderService;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderRepository, OrderEntity>
    implements OrderService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderEntity> page =
        this.page(new CommonQuery<OrderEntity>().getPage(params), new QueryWrapper<OrderEntity>());

    return new PageUtils(page);
  }
}
