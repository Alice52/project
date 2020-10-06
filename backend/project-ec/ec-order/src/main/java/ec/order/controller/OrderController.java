package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.OrderEntity;
import ec.order.service.OrderService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 订单
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/order")
public class OrderController {
  @Resource private OrderService orderService;

  @GetMapping("/list")
  // @RequiresPermissions("order:order:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = orderService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:order:info")
  public R info(@PathVariable("id") Long id) {
    OrderEntity order = orderService.getById(id);

    return R.ok().put("order", order);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:order:save")
  public R save(@RequestBody OrderEntity order) {
    orderService.save(order);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:order:update")
  public R update(@PathVariable("id") Long id, @RequestBody OrderEntity order) {
    order.setId(id);
    orderService.updateById(order);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:order:delete")
  public R delete(@RequestBody Long[] ids) {
    orderService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:order:delete")
  public R deleteById(@PathVariable("id") Long id) {
    orderService.removeById(id);

    return R.ok();
  }
}
