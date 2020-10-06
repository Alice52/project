package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.OrderOperateHistoryRepository;
import ec.order.entity.OrderOperateHistoryEntity;
import ec.order.service.OrderOperateHistoryService;

@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl
    extends ServiceImpl<OrderOperateHistoryRepository, OrderOperateHistoryEntity>
    implements OrderOperateHistoryService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderOperateHistoryEntity> page =
        this.page(
            new CommonQuery<OrderOperateHistoryEntity>().getPage(params),
            new QueryWrapper<OrderOperateHistoryEntity>());

    return new PageUtils(page);
  }
}
