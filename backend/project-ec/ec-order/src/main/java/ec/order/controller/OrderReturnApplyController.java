package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.OrderReturnApplyEntity;
import ec.order.service.OrderReturnApplyService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 订单退货申请
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/orderreturnapply")
public class OrderReturnApplyController {
  @Resource private OrderReturnApplyService orderReturnApplyService;

  @GetMapping("/list")
  // @RequiresPermissions("order:orderreturnapply:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = orderReturnApplyService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:orderreturnapply:info")
  public R info(@PathVariable("id") Long id) {
    OrderReturnApplyEntity orderReturnApply = orderReturnApplyService.getById(id);

    return R.ok().put("orderReturnApply", orderReturnApply);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:orderreturnapply:save")
  public R save(@RequestBody OrderReturnApplyEntity orderReturnApply) {
    orderReturnApplyService.save(orderReturnApply);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:orderreturnapply:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody OrderReturnApplyEntity orderReturnApply) {
    orderReturnApply.setId(id);
    orderReturnApplyService.updateById(orderReturnApply);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:orderreturnapply:delete")
  public R delete(@RequestBody Long[] ids) {
    orderReturnApplyService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:orderreturnapply:delete")
  public R deleteById(@PathVariable("id") Long id) {
    orderReturnApplyService.removeById(id);

    return R.ok();
  }
}
