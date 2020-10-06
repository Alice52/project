package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.PaymentInfoRepository;
import ec.order.entity.PaymentInfoEntity;
import ec.order.service.PaymentInfoService;

@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoRepository, PaymentInfoEntity>
    implements PaymentInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<PaymentInfoEntity> page =
        this.page(
            new CommonQuery<PaymentInfoEntity>().getPage(params),
            new QueryWrapper<PaymentInfoEntity>());

    return new PageUtils(page);
  }
}
