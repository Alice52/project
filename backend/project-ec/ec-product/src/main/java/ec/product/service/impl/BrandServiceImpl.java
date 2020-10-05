package ec.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

import ec.product.repository.BrandRepository;
import ec.product.entity.BrandEntity;
import ec.product.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandRepository, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(new CommonQuery<BrandEntity>().getPage(params), new QueryWrapper<BrandEntity>());

        return new PageUtils(page);
    }

}
