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
import ec.ware.model.entity.WareSkuEntity;
import ec.ware.service.WareSkuService;

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
    IPage<WareSkuEntity> page =
        this.page(
            new CommonQuery<WareSkuEntity>().getPage(params), new QueryWrapper<WareSkuEntity>());

    return new PageUtils(page);
  }
}
