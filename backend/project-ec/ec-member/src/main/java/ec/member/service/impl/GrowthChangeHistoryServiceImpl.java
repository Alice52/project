package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.GrowthChangeHistoryEntity;
import ec.member.repository.GrowthChangeHistoryRepository;
import ec.member.service.GrowthChangeHistoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl
    extends ServiceImpl<GrowthChangeHistoryRepository, GrowthChangeHistoryEntity>
    implements GrowthChangeHistoryService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<GrowthChangeHistoryEntity> page =
        this.page(
            new CommonQuery<GrowthChangeHistoryEntity>().getPage(params),
            new QueryWrapper<GrowthChangeHistoryEntity>());

    return new PageUtils(page);
  }
}
