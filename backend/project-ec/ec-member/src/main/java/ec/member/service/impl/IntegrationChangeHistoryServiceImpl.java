package ec.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.member.entity.IntegrationChangeHistoryEntity;
import ec.member.repository.IntegrationChangeHistoryRepository;
import ec.member.service.IntegrationChangeHistoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl
    extends ServiceImpl<IntegrationChangeHistoryRepository, IntegrationChangeHistoryEntity>
    implements IntegrationChangeHistoryService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<IntegrationChangeHistoryEntity> page =
        this.page(
            new CommonQuery<IntegrationChangeHistoryEntity>().getPage(params),
            new QueryWrapper<IntegrationChangeHistoryEntity>());

    return new PageUtils(page);
  }
}
