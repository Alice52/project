package ec.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.ware.repository.WareOrderTaskDetailRepository;
import ec.ware.entity.WareOrderTaskDetailEntity;
import ec.ware.service.WareOrderTaskDetailService;

@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl
    extends ServiceImpl<WareOrderTaskDetailRepository, WareOrderTaskDetailEntity>
    implements WareOrderTaskDetailService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<WareOrderTaskDetailEntity> page =
        this.page(
            new CommonQuery<WareOrderTaskDetailEntity>().getPage(params),
            new QueryWrapper<WareOrderTaskDetailEntity>());

    return new PageUtils(page);
  }
}
