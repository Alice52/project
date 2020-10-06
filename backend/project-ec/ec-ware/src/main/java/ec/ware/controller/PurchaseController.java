package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.entity.PurchaseEntity;
import ec.ware.service.PurchaseService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 采购信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:43:08
 */
@Api
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
  @Resource private PurchaseService purchaseService;

  @GetMapping("/list")
  // @RequiresPermissions("ware:purchase:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = purchaseService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("ware:purchase:info")
  public R info(@PathVariable("id") Long id) {
    PurchaseEntity purchase = purchaseService.getById(id);

    return R.ok().put("purchase", purchase);
  }

  @PostMapping("/save")
  // @RequiresPermissions("ware:purchase:save")
  public R save(@RequestBody PurchaseEntity purchase) {
    purchaseService.save(purchase);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("ware:purchase:update")
  public R update(@PathVariable("id") Long id, @RequestBody PurchaseEntity purchase) {
    purchase.setId(id);
    purchaseService.updateById(purchase);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("ware:purchase:delete")
  public R delete(@RequestBody Long[] ids) {
    purchaseService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("ware:purchase:delete")
  public R deleteById(@PathVariable("id") Long id) {
    purchaseService.removeById(id);

    return R.ok();
  }
}
