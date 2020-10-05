package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.BrandEntity;
import ec.product.service.BrandService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 品牌
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product/brand")
public class BrandController {
  @Resource private BrandService brandService;

  @GetMapping("/list")
  // @RequiresPermissions("product:brand:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = brandService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{brandId}")
  // @RequiresPermissions("product:brand:info")
  public R info(@PathVariable("brandId") Long brandId) {
    BrandEntity brand = brandService.getById(brandId);

    return R.ok().put("brand", brand);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:brand:save")
  public R save(@RequestBody BrandEntity brand) {
    brandService.save(brand);

    return R.ok();
  }

  @PutMapping("/update/{brandId}")
  // @RequiresPermissions("product:brand:update")
  public R update(@PathVariable("brandId") Long brandId, @RequestBody BrandEntity brand) {
    brand.setBrandId(brandId);
    brandService.updateById(brand);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:brand:delete")
  public R delete(@RequestBody Long[] brandIds) {
    brandService.removeByIds(Arrays.asList(brandIds));

    return R.ok();
  }

  @DeleteMapping("/delete/{brandId}")
  // @RequiresPermissions("product:brand:delete")
  public R deleteById(@PathVariable("brandId") Long brandId) {
    brandService.removeById(brandId);

    return R.ok();
  }
}
