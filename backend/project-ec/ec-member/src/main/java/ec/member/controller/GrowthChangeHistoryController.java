package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.member.entity.GrowthChangeHistoryEntity;
import ec.member.service.GrowthChangeHistoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 成长值变化历史记录
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/growthchangehistory")
public class GrowthChangeHistoryController {
  @Resource private GrowthChangeHistoryService growthChangeHistoryService;

  @GetMapping("/list")
  // @RequiresPermissions("member:growthchangehistory:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = growthChangeHistoryService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:growthchangehistory:info")
  public R info(@PathVariable("id") Long id) {
    GrowthChangeHistoryEntity growthChangeHistory = growthChangeHistoryService.getById(id);

    return R.ok().put("growthChangeHistory", growthChangeHistory);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:growthchangehistory:save")
  public R save(@RequestBody GrowthChangeHistoryEntity growthChangeHistory) {
    growthChangeHistoryService.save(growthChangeHistory);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:growthchangehistory:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody GrowthChangeHistoryEntity growthChangeHistory) {
    growthChangeHistory.setId(id);
    growthChangeHistoryService.updateById(growthChangeHistory);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:growthchangehistory:delete")
  public R delete(@RequestBody Long[] ids) {
    growthChangeHistoryService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:growthchangehistory:delete")
  public R deleteById(@PathVariable("id") Long id) {
    growthChangeHistoryService.removeById(id);

    return R.ok();
  }
}
