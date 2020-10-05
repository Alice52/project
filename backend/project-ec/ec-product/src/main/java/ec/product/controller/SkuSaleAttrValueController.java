package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SkuSaleAttrValueEntity;
import ec.product.service.SkuSaleAttrValueService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("product/skusaleattrvalue")
public class SkuSaleAttrValueController {
  @Resource private SkuSaleAttrValueService skuSaleAttrValueService;

  @GetMapping("/list")
  // @RequiresPermissions("product:skusaleattrvalue:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = skuSaleAttrValueService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:skusaleattrvalue:info")
  public R info(@PathVariable("id") Long id) {
    SkuSaleAttrValueEntity skuSaleAttrValue = skuSaleAttrValueService.getById(id);

    return R.ok().put("skuSaleAttrValue", skuSaleAttrValue);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:skusaleattrvalue:save")
  public R save(@RequestBody SkuSaleAttrValueEntity skuSaleAttrValue) {
    skuSaleAttrValueService.save(skuSaleAttrValue);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:skusaleattrvalue:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody SkuSaleAttrValueEntity skuSaleAttrValue) {
    skuSaleAttrValue.setId(id);
    skuSaleAttrValueService.updateById(skuSaleAttrValue);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:skusaleattrvalue:delete")
  public R delete(@RequestBody Long[] ids) {
    skuSaleAttrValueService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:skusaleattrvalue:delete")
  public R deleteById(@PathVariable("id") Long id) {
    skuSaleAttrValueService.removeById(id);

    return R.ok();
  }
}
