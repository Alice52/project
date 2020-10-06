package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.entity.WareOrderTaskEntity;
import ec.ware.service.WareOrderTaskService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 库存工作单
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:43:08
 */
@Api
@RestController
@RequestMapping("ware/wareordertask")
public class WareOrderTaskController {
  @Resource private WareOrderTaskService wareOrderTaskService;

  @GetMapping("/list")
  // @RequiresPermissions("ware:wareordertask:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = wareOrderTaskService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("ware:wareordertask:info")
  public R info(@PathVariable("id") Long id) {
    WareOrderTaskEntity wareOrderTask = wareOrderTaskService.getById(id);

    return R.ok().put("wareOrderTask", wareOrderTask);
  }

  @PostMapping("/save")
  // @RequiresPermissions("ware:wareordertask:save")
  public R save(@RequestBody WareOrderTaskEntity wareOrderTask) {
    wareOrderTaskService.save(wareOrderTask);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("ware:wareordertask:update")
  public R update(@PathVariable("id") Long id, @RequestBody WareOrderTaskEntity wareOrderTask) {
    wareOrderTask.setId(id);
    wareOrderTaskService.updateById(wareOrderTask);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("ware:wareordertask:delete")
  public R delete(@RequestBody Long[] ids) {
    wareOrderTaskService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("ware:wareordertask:delete")
  public R deleteById(@PathVariable("id") Long id) {
    wareOrderTaskService.removeById(id);

    return R.ok();
  }
}
