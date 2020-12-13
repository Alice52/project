package ec.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.converter.SpuInfoConverter;
import ec.product.entity.*;
import ec.product.feign.CouponFeignService;
import ec.product.model.to.SkuReductionTO;
import ec.product.model.to.SpuBoundTO;
import ec.product.model.vo.*;
import ec.product.repository.SpuInfoRepository;
import ec.product.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** @author zack */
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoRepository, SpuInfoEntity>
    implements SpuInfoService {

  @Resource private SpuInfoDescService spuInfoDescService;
  @Resource private SpuImagesService spuImagesService;
  @Resource private AttrService attrService;
  @Resource private ProductAttrValueService attrValueService;
  @Resource private SkuInfoService skuInfoService;
  @Resource private SkuImagesService skuImagesService;
  @Resource private SkuSaleAttrValueService saleAttrValueService;
  @Resource private CouponFeignService couponFeignService;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<SpuInfoEntity> page =
        this.page(
            new CommonQuery<SpuInfoEntity>().getPage(params), new QueryWrapper<SpuInfoEntity>());

    return new PageUtils(page);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveSpuInfo(SpuSaveVO vo) {
    // 1. save spu basic info
    SpuInfoEntity spu = SpuInfoConverter.INSTANCE.vo2po(vo);
    this.saveSpuBaseInfo(spu);

    // 2. save spu decript images
    spuInfoDescService.saveSpuInfoDesc(
        new SpuInfoDescEntity(spu.getId(), String.join(",", vo.getDecript())));

    // 3. save spu images
    spuImagesService.saveImages(spu.getId(), vo.getImages());

    // 4. save spu base attr
    List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
    List<Long> attrIds =
        baseAttrs.parallelStream().map(x -> x.getAttrId()).collect(Collectors.toList());
    Map<Long, String> attrIdAndNameMap =
        attrService.listByIds(attrIds).stream()
            .collect(Collectors.toMap(AttrEntity::getAttrId, AttrEntity::getAttrName));

    List<ProductAttrValueEntity> attrValueEntities =
        baseAttrs.stream()
            .map(
                x -> {
                  ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
                  attrValueEntity.setAttrId(x.getAttrId());
                  attrValueEntity.setAttrName(attrIdAndNameMap.get(x.getAttrId()));
                  attrValueEntity.setAttrValue(x.getAttrValues());
                  attrValueEntity.setQuickShow(x.getShowDesc());
                  attrValueEntity.setSpuId(spu.getId());

                  return attrValueEntity;
                })
            .collect(Collectors.toList());
    attrValueService.saveAttrValues(attrValueEntities);

    // 5. save spu bound info
    Bounds bounds = vo.getBounds();
    if (ObjectUtil.isNotNull(bounds)) {
      R r =
          couponFeignService.saveSpuBounds(
              new SpuBoundTO(spu.getId(), bounds.getBuyBounds(), bounds.getGrowBounds()));
      if (r.getCode() != 0) {
        log.error("call couponFeignService saveSpuBounds failed!");
      }
    }

    // 6. save relevant skus: basic info, images, sale att, coupon info
    List<Skus> skus = vo.getSkus();
    if (!skus.isEmpty()) {
      skus.forEach(
          x -> {
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            skuInfoEntity.setSkuName(x.getSkuName());
            skuInfoEntity.setPrice(x.getPrice());
            skuInfoEntity.setSkuTitle(x.getSkuTitle());
            skuInfoEntity.setSkuSubtitle(x.getSkuSubtitle());
            skuInfoEntity.setBrandId(spu.getBrandId());
            skuInfoEntity.setCatalogId(spu.getCatalogId());
            skuInfoEntity.setSaleCount(0L);
            skuInfoEntity.setSpuId(spu.getId());
            List<Images> images = x.getImages();
            String defaultImg = "";
            for (Images image : images) {
              if (image.getDefaultImg() == 1) {
                defaultImg = image.getImgUrl();
              }
            }
            skuInfoEntity.setSkuDefaultImg(defaultImg);
            skuInfoService.saveSkuInfo(skuInfoEntity);

            // save sku images
            if (!images.isEmpty()) {
              List<SkuImagesEntity> skuImagesEntities =
                  images.stream()
                      .map(
                          y ->
                              new SkuImagesEntity(
                                  skuInfoEntity.getSkuId(), y.getImgUrl(), y.getDefaultImg()))
                      .filter(img -> img.getImgUrl() != null)
                      .collect(Collectors.toList());
              skuImagesService.saveSkuImages(skuImagesEntities);
            }

            List<Attr> attrs = x.getAttr();
            if (!attrs.isEmpty()) {
              List<SkuSaleAttrValueEntity> saleAttrValueEntities =
                  attrs.stream()
                      .map(
                          attr ->
                              new SkuSaleAttrValueEntity(
                                  attr.getAttrId(),
                                  attr.getAttrName(),
                                  attr.getAttrValue(),
                                  skuInfoEntity.getSkuId()))
                      .collect(Collectors.toList());
              saleAttrValueService.saveSaleAttrValues(saleAttrValueEntities);
            }

            // save sku reduction  info
            SkuReductionTO reductionTO = new SkuReductionTO();
            BeanUtil.copyProperties(x, reductionTO);
            reductionTO.setSkuId(skuInfoEntity.getSkuId());

            if (reductionTO.getFullCount() > 0
                || reductionTO.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
              R r = couponFeignService.saveSkuReductionInfo(reductionTO);
              if (r.getCode() != 0) {
                log.error("call couponFeignService saveSkuReductionInfo failed!");
              }
            }
          });
    }
  }

  @Override
  public void saveSpuBaseInfo(SpuInfoEntity entity) {
    baseMapper.insert(entity);
  }

  @Override
  public PageUtils queryPageByCondition(Map<String, Object> params) {

    QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
    Object key = params.get("key");
    if (ObjectUtil.isNotEmpty(key)) {
      wrapper.and(w -> w.eq("id", key).or().like("spu_name", key));
    }

    Object status = params.get("status");
    if (ObjectUtil.isNotEmpty(status)) {
      wrapper.eq("publish_status", status);
    }

    Object brandId = params.get("brandId");
    if (ObjectUtil.isNotEmpty(brandId) && !"0".equalsIgnoreCase(brandId.toString())) {
      wrapper.eq("brand_id", brandId);
    }

    Object catelogId = params.get("catelogId");
    if (ObjectUtil.isNotEmpty(catelogId) && !"0".equalsIgnoreCase(brandId.toString())) {
      wrapper.eq("catalog_id", catelogId);
    }

    IPage<SpuInfoEntity> page =
        this.page(new CommonQuery<SpuInfoEntity>().getPage(params), wrapper);

    return new PageUtils(page);
  }

  @Override
  public void spuDown(Long spuId) {
    this.update(new UpdateWrapper<SpuInfoEntity>().eq("id", spuId).set("publish_status", 2));
  }

  @Override
  public void spuUp(Long spuId) {
    this.update(new UpdateWrapper<SpuInfoEntity>().eq("id", spuId).set("publish_status", 1));
  }
}
