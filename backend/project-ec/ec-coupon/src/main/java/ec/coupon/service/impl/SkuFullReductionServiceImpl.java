package ec.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.coupon.converter.MemberPriceConverter;
import ec.coupon.converter.SkuLadderConverter;
import ec.coupon.entity.MemberPriceEntity;
import ec.coupon.entity.SkuFullReductionEntity;
import ec.coupon.model.to.MemberPrice;
import ec.coupon.model.to.SkuReductionTO;
import ec.coupon.repository.SkuFullReductionRepository;
import ec.coupon.service.MemberPriceService;
import ec.coupon.service.SkuFullReductionService;
import ec.coupon.service.SkuLadderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ec.coupon.converter.SkuFullReductionConverter.INSTANCE;

/** @author zack */
@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl
    extends ServiceImpl<SkuFullReductionRepository, SkuFullReductionEntity>
    implements SkuFullReductionService {

  @Resource private SkuLadderService ladderService;
  @Resource private MemberPriceService memberPriceService;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {

    IPage<SkuFullReductionEntity> page =
        this.page(
            new CommonQuery<SkuFullReductionEntity>().getPage(params),
            new QueryWrapper<SkuFullReductionEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveSkuReduction(SkuReductionTO to) {
    if (to.getFullCount() > 0) {
      ladderService.saveSkuLadder(SkuLadderConverter.INSTANCE.to2po(to));
    }

    if (to.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
      this.save(INSTANCE.to2po(to));
    }

    List<MemberPrice> memberPrice = to.getMemberPrice();
    if (!memberPrice.isEmpty()) {
      List<MemberPriceEntity> priceEntities =
          memberPrice.stream()
              .map(price -> MemberPriceConverter.INSTANCE.to2po(price, to.getSkuId()))
              .filter(p -> p.getMemberPrice().compareTo(new BigDecimal("0")) == 1)
              .collect(Collectors.toList());

      memberPriceService.saveMemberPrices(priceEntities);
    }
  }
}
