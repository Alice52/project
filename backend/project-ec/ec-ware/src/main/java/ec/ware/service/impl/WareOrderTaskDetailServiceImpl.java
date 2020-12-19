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
import ec.ware.model.entity.WareOrderTaskDetailEntity;
import ec.ware.service.WareOrderTaskDetailService;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
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
