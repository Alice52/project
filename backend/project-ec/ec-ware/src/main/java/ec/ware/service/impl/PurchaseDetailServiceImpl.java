package ec.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.ware.repository.PurchaseDetailRepository;
import ec.ware.entity.PurchaseDetailEntity;
import ec.ware.service.PurchaseDetailService;

@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl
    extends ServiceImpl<PurchaseDetailRepository, PurchaseDetailEntity>
    implements PurchaseDetailService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<PurchaseDetailEntity> page =
        this.page(
            new CommonQuery<PurchaseDetailEntity>().getPage(params),
            new QueryWrapper<PurchaseDetailEntity>());

    return new PageUtils(page);
  }
}
