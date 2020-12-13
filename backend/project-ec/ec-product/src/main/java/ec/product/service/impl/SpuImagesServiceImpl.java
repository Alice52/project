package ec.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.product.entity.SpuImagesEntity;
import ec.product.repository.SpuImagesRepository;
import ec.product.service.SpuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesRepository, SpuImagesEntity>
    implements SpuImagesService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuImagesEntity> page =
        this.page(
            new CommonQuery<SpuImagesEntity>().getPage(params),
            new QueryWrapper<SpuImagesEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveImages(Long spuId, List<String> images) {

    if (ObjectUtil.isNotNull(images) && !images.isEmpty()) {
      List<SpuImagesEntity> entities =
          images.stream().map(x -> new SpuImagesEntity(spuId, x)).collect(Collectors.toList());
      this.saveBatch(entities);
    }
  }
}
