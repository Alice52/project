package ec.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.product.repository.SpuCommentRepository;
import ec.product.entity.SpuCommentEntity;
import ec.product.service.SpuCommentService;

@Service("spuCommentService")
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentRepository, SpuCommentEntity>
    implements SpuCommentService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuCommentEntity> page =
        this.page(
            new CommonQuery<SpuCommentEntity>().getPage(params),
            new QueryWrapper<SpuCommentEntity>());

    return new PageUtils(page);
  }
}
