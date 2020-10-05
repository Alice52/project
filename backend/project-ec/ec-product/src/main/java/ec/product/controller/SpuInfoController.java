package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SpuInfoEntity;
import ec.product.service.SpuInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * spu信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
  @Resource private SpuInfoService spuInfoService;

  @GetMapping("/list")
  // @RequiresPermissions("product:spuinfo:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuInfoService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:spuinfo:info")
  public R info(@PathVariable("id") Long id) {
    SpuInfoEntity spuInfo = spuInfoService.getById(id);

    return R.ok().put("spuInfo", spuInfo);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:spuinfo:save")
  public R save(@RequestBody SpuInfoEntity spuInfo) {
    spuInfoService.save(spuInfo);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:spuinfo:update")
  public R update(@PathVariable("id") Long id, @RequestBody SpuInfoEntity spuInfo) {
    spuInfo.setId(id);
    spuInfoService.updateById(spuInfo);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:spuinfo:delete")
  public R delete(@RequestBody Long[] ids) {
    spuInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:spuinfo:delete")
  public R deleteById(@PathVariable("id") Long id) {
    spuInfoService.removeById(id);

    return R.ok();
  }
}
