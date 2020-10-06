package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.OrderSettingEntity;
import ec.order.service.OrderSettingService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 订单配置信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/ordersetting")
public class OrderSettingController {
  @Resource private OrderSettingService orderSettingService;

  @GetMapping("/list")
  // @RequiresPermissions("order:ordersetting:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = orderSettingService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:ordersetting:info")
  public R info(@PathVariable("id") Long id) {
    OrderSettingEntity orderSetting = orderSettingService.getById(id);

    return R.ok().put("orderSetting", orderSetting);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:ordersetting:save")
  public R save(@RequestBody OrderSettingEntity orderSetting) {
    orderSettingService.save(orderSetting);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:ordersetting:update")
  public R update(@PathVariable("id") Long id, @RequestBody OrderSettingEntity orderSetting) {
    orderSetting.setId(id);
    orderSettingService.updateById(orderSetting);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:ordersetting:delete")
  public R delete(@RequestBody Long[] ids) {
    orderSettingService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:ordersetting:delete")
  public R deleteById(@PathVariable("id") Long id) {
    orderSettingService.removeById(id);

    return R.ok();
  }
}
