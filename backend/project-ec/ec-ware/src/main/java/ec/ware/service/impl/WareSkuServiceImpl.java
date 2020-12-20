package ec.ware.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.ware.model.entity.WareSkuEntity;
import ec.ware.repository.WareSkuRepository;
import ec.ware.service.WareSkuService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品库存
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuRepository, WareSkuEntity>
    implements WareSkuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {

    QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();

    Object skuId = params.get("skuId");
    if (ObjectUtil.isNotNull(skuId) && StrUtil.isNotBlank(skuId.toString())) {
      wrapper.eq("sku_id", skuId);
    }

    Object wareId = params.get("wareId");
    if (ObjectUtil.isNotNull(wareId) && StrUtil.isNotBlank(wareId.toString())) {
      wrapper.eq("ware_id", wareId);
    }

    IPage<WareSkuEntity> page =
        this.page(new CommonQuery<WareSkuEntity>().getPage(params), wrapper);

    return new PageUtils(page);
  }
}
