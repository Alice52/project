package ec.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.order.repository.RefundInfoRepository;
import ec.order.entity.RefundInfoEntity;
import ec.order.service.RefundInfoService;

@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoRepository, RefundInfoEntity>
    implements RefundInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<RefundInfoEntity> page =
        this.page(
            new CommonQuery<RefundInfoEntity>().getPage(params),
            new QueryWrapper<RefundInfoEntity>());

    return new PageUtils(page);
  }
}
