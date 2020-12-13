package ec.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.product.repository.SpuInfoDescRepository;
import ec.product.entity.SpuInfoDescEntity;
import ec.product.service.SpuInfoDescService;

@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescRepository, SpuInfoDescEntity>
    implements SpuInfoDescService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuInfoDescEntity> page =
        this.page(
            new CommonQuery<SpuInfoDescEntity>().getPage(params),
            new QueryWrapper<SpuInfoDescEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveSpuInfoDesc(SpuInfoDescEntity spuInfoDescEntity) {
    this.baseMapper.insert(spuInfoDescEntity);
  }
}
