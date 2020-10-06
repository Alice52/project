package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.OrderItemEntity;
import ec.order.service.OrderItemService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 订单项信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/orderitem")
public class OrderItemController {
  @Resource private OrderItemService orderItemService;

  @GetMapping("/list")
  // @RequiresPermissions("order:orderitem:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = orderItemService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:orderitem:info")
  public R info(@PathVariable("id") Long id) {
    OrderItemEntity orderItem = orderItemService.getById(id);

    return R.ok().put("orderItem", orderItem);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:orderitem:save")
  public R save(@RequestBody OrderItemEntity orderItem) {
    orderItemService.save(orderItem);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:orderitem:update")
  public R update(@PathVariable("id") Long id, @RequestBody OrderItemEntity orderItem) {
    orderItem.setId(id);
    orderItemService.updateById(orderItem);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:orderitem:delete")
  public R delete(@RequestBody Long[] ids) {
    orderItemService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:orderitem:delete")
  public R deleteById(@PathVariable("id") Long id) {
    orderItemService.removeById(id);

    return R.ok();
  }
}
