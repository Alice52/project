package ec.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.ware.repository.WareOrderTaskRepository;
import ec.ware.model.entity.WareOrderTaskEntity;
import ec.ware.service.WareOrderTaskService;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl
    extends ServiceImpl<WareOrderTaskRepository, WareOrderTaskEntity>
    implements WareOrderTaskService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<WareOrderTaskEntity> page =
        this.page(
            new CommonQuery<WareOrderTaskEntity>().getPage(params),
            new QueryWrapper<WareOrderTaskEntity>());

    return new PageUtils(page);
  }
}
