package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.OrderReturnApplyRepository;
import ec.order.entity.OrderReturnApplyEntity;
import ec.order.service.OrderReturnApplyService;

@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl
    extends ServiceImpl<OrderReturnApplyRepository, OrderReturnApplyEntity>
    implements OrderReturnApplyService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderReturnApplyEntity> page =
        this.page(
            new CommonQuery<OrderReturnApplyEntity>().getPage(params),
            new QueryWrapper<OrderReturnApplyEntity>());

    return new PageUtils(page);
  }
}
