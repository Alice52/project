package ec.ware.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.ware.model.entity.PurchaseDetailEntity;
import ec.ware.repository.PurchaseDetailRepository;
import ec.ware.service.PurchaseDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl
    extends ServiceImpl<PurchaseDetailRepository, PurchaseDetailEntity>
    implements PurchaseDetailService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {

    QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();

    Object key = params.get("key");
    if (ObjectUtil.isNotNull(key) && StrUtil.isNotBlank(key.toString())) {
      wrapper.and(w -> w.eq("purchase_id", key).or().eq("sku_id", key));
    }

    Object status = params.get("status");
    if (ObjectUtil.isNotNull(status) && StrUtil.isNotBlank(status.toString())) {
      wrapper.eq("status", status);
    }

    Object wareId = params.get("wareId");
    if (ObjectUtil.isNotNull(wareId) && StrUtil.isNotBlank(wareId.toString())) {
      wrapper.eq("ware_id", wareId);
    }

    IPage<PurchaseDetailEntity> page =
        this.page(new CommonQuery<PurchaseDetailEntity>().getPage(params), wrapper);

    return new PageUtils(page);
  }

  @Override
  public List<PurchaseDetailEntity> listByPurchaseId(Long purchaseId) {

    List<PurchaseDetailEntity> entities =
        this.list(new QueryWrapper<PurchaseDetailEntity>().eq("purchase_id", purchaseId));

    return entities;
  }
}
