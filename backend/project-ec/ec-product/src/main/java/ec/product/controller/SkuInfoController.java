package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SkuInfoEntity;
import ec.product.service.SkuInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * sku信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("/product")
public class SkuInfoController {
  @Resource private SkuInfoService skuInfoService;

  @GetMapping("/sku-info")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = skuInfoService.queryPageByCondition(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/sku-info/{skuId}")
  public R info(@PathVariable("skuId") Long skuId) {
    SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

    return R.ok().put("skuInfo", skuInfo);
  }

  @PostMapping("/sku-info")
  public R save(@RequestBody SkuInfoEntity skuInfo) {
    skuInfoService.save(skuInfo);

    return R.ok();
  }

  @PutMapping("/sku-info/{skuId}")
  public R update(@PathVariable("skuId") Long skuId, @RequestBody SkuInfoEntity skuInfo) {
    skuInfo.setSkuId(skuId);
    skuInfoService.updateById(skuInfo);

    return R.ok();
  }

  @DeleteMapping("/sku-info")
  public R delete(@RequestBody Long[] skuIds) {
    skuInfoService.removeByIds(Arrays.asList(skuIds));

    return R.ok();
  }

  @DeleteMapping("/sku-info/{skuId}")
  public R deleteById(@PathVariable("skuId") Long skuId) {
    skuInfoService.removeById(skuId);

    return R.ok();
  }
}
