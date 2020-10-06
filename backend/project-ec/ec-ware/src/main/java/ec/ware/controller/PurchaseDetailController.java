package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.entity.PurchaseDetailEntity;
import ec.ware.service.PurchaseDetailService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:43:08
 */
@Api
@RestController
@RequestMapping("ware/purchasedetail")
public class PurchaseDetailController {
  @Resource private PurchaseDetailService purchaseDetailService;

  @GetMapping("/list")
  // @RequiresPermissions("ware:purchasedetail:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = purchaseDetailService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("ware:purchasedetail:info")
  public R info(@PathVariable("id") Long id) {
    PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);

    return R.ok().put("purchaseDetail", purchaseDetail);
  }

  @PostMapping("/save")
  // @RequiresPermissions("ware:purchasedetail:save")
  public R save(@RequestBody PurchaseDetailEntity purchaseDetail) {
    purchaseDetailService.save(purchaseDetail);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("ware:purchasedetail:update")
  public R update(@PathVariable("id") Long id, @RequestBody PurchaseDetailEntity purchaseDetail) {
    purchaseDetail.setId(id);
    purchaseDetailService.updateById(purchaseDetail);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("ware:purchasedetail:delete")
  public R delete(@RequestBody Long[] ids) {
    purchaseDetailService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("ware:purchasedetail:delete")
  public R deleteById(@PathVariable("id") Long id) {
    purchaseDetailService.removeById(id);

    return R.ok();
  }
}
