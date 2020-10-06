package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.entity.WareOrderTaskDetailEntity;
import ec.ware.service.WareOrderTaskDetailService;
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
@RequestMapping("ware/wareordertaskdetail")
public class WareOrderTaskDetailController {
  @Resource private WareOrderTaskDetailService wareOrderTaskDetailService;

  @GetMapping("/list")
  // @RequiresPermissions("ware:wareordertaskdetail:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = wareOrderTaskDetailService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("ware:wareordertaskdetail:info")
  public R info(@PathVariable("id") Long id) {
    WareOrderTaskDetailEntity wareOrderTaskDetail = wareOrderTaskDetailService.getById(id);

    return R.ok().put("wareOrderTaskDetail", wareOrderTaskDetail);
  }

  @PostMapping("/save")
  // @RequiresPermissions("ware:wareordertaskdetail:save")
  public R save(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
    wareOrderTaskDetailService.save(wareOrderTaskDetail);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("ware:wareordertaskdetail:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
    wareOrderTaskDetail.setId(id);
    wareOrderTaskDetailService.updateById(wareOrderTaskDetail);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("ware:wareordertaskdetail:delete")
  public R delete(@RequestBody Long[] ids) {
    wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("ware:wareordertaskdetail:delete")
  public R deleteById(@PathVariable("id") Long id) {
    wareOrderTaskDetailService.removeById(id);

    return R.ok();
  }
}
