package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.OrderOperateHistoryEntity;
import ec.order.service.OrderOperateHistoryService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 订单操作历史记录
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/orderoperatehistory")
public class OrderOperateHistoryController {
  @Resource private OrderOperateHistoryService orderOperateHistoryService;

  @GetMapping("/list")
  // @RequiresPermissions("order:orderoperatehistory:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = orderOperateHistoryService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:orderoperatehistory:info")
  public R info(@PathVariable("id") Long id) {
    OrderOperateHistoryEntity orderOperateHistory = orderOperateHistoryService.getById(id);

    return R.ok().put("orderOperateHistory", orderOperateHistory);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:orderoperatehistory:save")
  public R save(@RequestBody OrderOperateHistoryEntity orderOperateHistory) {
    orderOperateHistoryService.save(orderOperateHistory);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:orderoperatehistory:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody OrderOperateHistoryEntity orderOperateHistory) {
    orderOperateHistory.setId(id);
    orderOperateHistoryService.updateById(orderOperateHistory);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:orderoperatehistory:delete")
  public R delete(@RequestBody Long[] ids) {
    orderOperateHistoryService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:orderoperatehistory:delete")
  public R deleteById(@PathVariable("id") Long id) {
    orderOperateHistoryService.removeById(id);

    return R.ok();
  }
}
