package ec.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.ware.repository.WareInfoRepository;
import ec.ware.model.entity.WareInfoEntity;
import ec.ware.service.WareInfoService;

/**
 * 仓库信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoRepository, WareInfoEntity>
    implements WareInfoService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<WareInfoEntity> page =
        this.page(
            new CommonQuery<WareInfoEntity>().getPage(params), new QueryWrapper<WareInfoEntity>());

    return new PageUtils(page);
  }
}
