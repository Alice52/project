package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SpuInfoDescEntity;
import ec.product.service.SpuInfoDescService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * spu信息介绍
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("product/spuinfodesc")
public class SpuInfoDescController {
  @Resource private SpuInfoDescService spuInfoDescService;

  @GetMapping("/list")
  // @RequiresPermissions("product:spuinfodesc:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuInfoDescService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{spuId}")
  // @RequiresPermissions("product:spuinfodesc:info")
  public R info(@PathVariable("spuId") Long spuId) {
    SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(spuId);

    return R.ok().put("spuInfoDesc", spuInfoDesc);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:spuinfodesc:save")
  public R save(@RequestBody SpuInfoDescEntity spuInfoDesc) {
    spuInfoDescService.save(spuInfoDesc);

    return R.ok();
  }

  @PutMapping("/update/{spuId}")
  // @RequiresPermissions("product:spuinfodesc:update")
  public R update(@PathVariable("spuId") Long spuId, @RequestBody SpuInfoDescEntity spuInfoDesc) {
    spuInfoDesc.setSpuId(spuId);
    spuInfoDescService.updateById(spuInfoDesc);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:spuinfodesc:delete")
  public R delete(@RequestBody Long[] spuIds) {
    spuInfoDescService.removeByIds(Arrays.asList(spuIds));

    return R.ok();
  }

  @DeleteMapping("/delete/{spuId}")
  // @RequiresPermissions("product:spuinfodesc:delete")
  public R deleteById(@PathVariable("spuId") Long spuId) {
    spuInfoDescService.removeById(spuId);

    return R.ok();
  }
}
