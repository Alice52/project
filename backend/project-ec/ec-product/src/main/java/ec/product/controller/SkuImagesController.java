package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SkuImagesEntity;
import ec.product.service.SkuImagesService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * sku图片
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("product/skuimages")
public class SkuImagesController {
  @Resource private SkuImagesService skuImagesService;

  @GetMapping("/list")
  // @RequiresPermissions("product:skuimages:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = skuImagesService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:skuimages:info")
  public R info(@PathVariable("id") Long id) {
    SkuImagesEntity skuImages = skuImagesService.getById(id);

    return R.ok().put("skuImages", skuImages);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:skuimages:save")
  public R save(@RequestBody SkuImagesEntity skuImages) {
    skuImagesService.save(skuImages);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:skuimages:update")
  public R update(@PathVariable("id") Long id, @RequestBody SkuImagesEntity skuImages) {
    skuImages.setId(id);
    skuImagesService.updateById(skuImages);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:skuimages:delete")
  public R delete(@RequestBody Long[] ids) {
    skuImagesService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:skuimages:delete")
  public R deleteById(@PathVariable("id") Long id) {
    skuImagesService.removeById(id);

    return R.ok();
  }
}
