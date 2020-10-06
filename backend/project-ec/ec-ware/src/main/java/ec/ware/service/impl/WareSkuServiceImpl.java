package ec.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.ware.repository.WareSkuRepository;
import ec.ware.entity.WareSkuEntity;
import ec.ware.service.WareSkuService;

@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuRepository, WareSkuEntity>
    implements WareSkuService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<WareSkuEntity> page =
        this.page(
            new CommonQuery<WareSkuEntity>().getPage(params), new QueryWrapper<WareSkuEntity>());

    return new PageUtils(page);
  }
}
