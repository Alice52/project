package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.RefundInfoEntity;
import ec.order.service.RefundInfoService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 退款信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/refundinfo")
public class RefundInfoController {
  @Resource private RefundInfoService refundInfoService;

  @GetMapping("/list")
  // @RequiresPermissions("order:refundinfo:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = refundInfoService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:refundinfo:info")
  public R info(@PathVariable("id") Long id) {
    RefundInfoEntity refundInfo = refundInfoService.getById(id);

    return R.ok().put("refundInfo", refundInfo);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:refundinfo:save")
  public R save(@RequestBody RefundInfoEntity refundInfo) {
    refundInfoService.save(refundInfo);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:refundinfo:update")
  public R update(@PathVariable("id") Long id, @RequestBody RefundInfoEntity refundInfo) {
    refundInfo.setId(id);
    refundInfoService.updateById(refundInfo);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:refundinfo:delete")
  public R delete(@RequestBody Long[] ids) {
    refundInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:refundinfo:delete")
  public R deleteById(@PathVariable("id") Long id) {
    refundInfoService.removeById(id);

    return R.ok();
  }
}
