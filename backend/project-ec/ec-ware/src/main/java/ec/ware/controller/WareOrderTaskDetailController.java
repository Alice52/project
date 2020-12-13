package ec.ware.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.ware.entity.WareOrderTaskDetailEntity;
import ec.ware.service.WareOrderTaskDetailService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

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
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = wareOrderTaskDetailService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    WareOrderTaskDetailEntity wareOrderTaskDetail = wareOrderTaskDetailService.getById(id);

    return R.ok().put("wareOrderTaskDetail", wareOrderTaskDetail);
  }

  @PostMapping("/save")
  public R save(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
    wareOrderTaskDetailService.save(wareOrderTaskDetail);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  public R update(
      @PathVariable("id") Long id, @RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
    wareOrderTaskDetail.setId(id);
    wareOrderTaskDetailService.updateById(wareOrderTaskDetail);

    return R.ok();
  }

  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  public R deleteById(@PathVariable("id") Long id) {
    wareOrderTaskDetailService.removeById(id);

    return R.ok();
  }
}
