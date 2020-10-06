package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SpuBoundsEntity;
import ec.coupon.service.SpuBoundsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController extends BaseController {
  @Resource private SpuBoundsService spuBoundsService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:spubounds:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuBoundsService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:spubounds:info")
  public SpuBoundsEntity info(@PathVariable("id") Long id) {
    SpuBoundsEntity spuBounds = spuBoundsService.getById(id);

    return spuBounds;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:spubounds:save")
  public void save(@RequestBody SpuBoundsEntity spuBounds) {
    spuBoundsService.save(spuBounds);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:spubounds:update")
  public void update(@PathVariable("id") Long id, @RequestBody SpuBoundsEntity spuBounds) {
    spuBounds.setId(id);
    spuBoundsService.updateById(spuBounds);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:spubounds:delete")
  public void delete(@RequestBody Long[] ids) {
    spuBoundsService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:spubounds:delete")
  public void deleteById(@PathVariable("id") Long id) {
    spuBoundsService.removeById(id);
  }
}
