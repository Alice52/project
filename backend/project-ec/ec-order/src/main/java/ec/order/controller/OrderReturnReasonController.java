package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.OrderReturnReasonEntity;
import ec.order.service.OrderReturnReasonService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 退货原因
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/orderreturnreason")
public class OrderReturnReasonController {
  @Resource private OrderReturnReasonService orderReturnReasonService;

  @GetMapping("/list")
  // @RequiresPermissions("order:orderreturnreason:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = orderReturnReasonService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:orderreturnreason:info")
  public R info(@PathVariable("id") Long id) {
    OrderReturnReasonEntity orderReturnReason = orderReturnReasonService.getById(id);

    return R.ok().put("orderReturnReason", orderReturnReason);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:orderreturnreason:save")
  public R save(@RequestBody OrderReturnReasonEntity orderReturnReason) {
    orderReturnReasonService.save(orderReturnReason);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:orderreturnreason:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody OrderReturnReasonEntity orderReturnReason) {
    orderReturnReason.setId(id);
    orderReturnReasonService.updateById(orderReturnReason);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:orderreturnreason:delete")
  public R delete(@RequestBody Long[] ids) {
    orderReturnReasonService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:orderreturnreason:delete")
  public R deleteById(@PathVariable("id") Long id) {
    orderReturnReasonService.removeById(id);

    return R.ok();
  }
}
