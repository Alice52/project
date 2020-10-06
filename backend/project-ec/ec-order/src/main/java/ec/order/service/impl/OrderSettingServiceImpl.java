package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.OrderSettingRepository;
import ec.order.entity.OrderSettingEntity;
import ec.order.service.OrderSettingService;

@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingRepository, OrderSettingEntity>
    implements OrderSettingService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<OrderSettingEntity> page =
        this.page(
            new CommonQuery<OrderSettingEntity>().getPage(params),
            new QueryWrapper<OrderSettingEntity>());

    return new PageUtils(page);
  }
}
