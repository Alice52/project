package ec.product.controller;

import ec.common.annotation.AddGroup;
import ec.common.annotation.UpdateGroup;
import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.BrandEntity;
import ec.product.model.BrandVO;
import ec.product.service.BrandService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.Map;

import static ec.product.converter.BrandConverter.INSTANCE;

/**
 * 品牌
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product")
public class BrandController {
  @Resource private BrandService brandService;

  @GetMapping("/brands")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = brandService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/brand/{brandId}")
  public R info(@PathVariable("brandId") Long brandId) {
    BrandEntity brand = brandService.getById(brandId);

    return R.ok().put("brand", brand);
  }

  @PostMapping("/brands")
  public R save(@RequestBody @Validated({AddGroup.class, Default.class}) BrandVO brand) {

    BrandEntity brandEntity = INSTANCE.vo2po(brand);
    brandService.save(brandEntity);

    return R.ok();
  }

  @PutMapping("/brand/{brandId}")
  public R update(
      @PathVariable("brandId") Long brandId,
      @RequestBody @Validated({UpdateGroup.class, Default.class}) BrandVO brandVO) {

    brandVO.setBrandId(brandId);
    BrandEntity entity = INSTANCE.vo2po(brandVO);
    brandService.updateById(entity);

    return R.ok();
  }

  @DeleteMapping("/brands")
  public R delete(@RequestBody @NotEmpty Long[] brandIds) {
    brandService.removeByIds(Arrays.asList(brandIds));

    return R.ok();
  }

  @DeleteMapping("/brand/{brandId}")
  // @RequiresPermissions("product:brand:delete")
  public R deleteById(@PathVariable("brandId") Long brandId) {
    brandService.removeById(brandId);

    return R.ok();
  }
}
