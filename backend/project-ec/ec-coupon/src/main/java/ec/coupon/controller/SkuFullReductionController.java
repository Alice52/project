package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SkuFullReductionEntity;
import ec.coupon.service.SkuFullReductionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品满减信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/skufullreduction")
public class SkuFullReductionController extends BaseController {
  @Resource private SkuFullReductionService skuFullReductionService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:skufullreduction:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = skuFullReductionService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:skufullreduction:info")
  public SkuFullReductionEntity info(@PathVariable("id") Long id) {
    SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);

    return skuFullReduction;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:skufullreduction:save")
  public void save(@RequestBody SkuFullReductionEntity skuFullReduction) {
    skuFullReductionService.save(skuFullReduction);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:skufullreduction:update")
  public void update(
      @PathVariable("id") Long id, @RequestBody SkuFullReductionEntity skuFullReduction) {
    skuFullReduction.setId(id);
    skuFullReductionService.updateById(skuFullReduction);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:skufullreduction:delete")
  public void delete(@RequestBody Long[] ids) {
    skuFullReductionService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:skufullreduction:delete")
  public void deleteById(@PathVariable("id") Long id) {
    skuFullReductionService.removeById(id);
  }
}
