package ec.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.ware.repository.PurchaseRepository;
import ec.ware.entity.PurchaseEntity;
import ec.ware.service.PurchaseService;

@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseRepository, PurchaseEntity>
    implements PurchaseService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<PurchaseEntity> page =
        this.page(
            new CommonQuery<PurchaseEntity>().getPage(params), new QueryWrapper<PurchaseEntity>());

    return new PageUtils(page);
  }
}
