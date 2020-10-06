package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.member.entity.IntegrationChangeHistoryEntity;
import ec.member.service.IntegrationChangeHistoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/integrationchangehistory")
public class IntegrationChangeHistoryController {
  @Resource private IntegrationChangeHistoryService integrationChangeHistoryService;

  @GetMapping("/list")
  // @RequiresPermissions("member:integrationchangehistory:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = integrationChangeHistoryService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:integrationchangehistory:info")
  public R info(@PathVariable("id") Long id) {
    IntegrationChangeHistoryEntity integrationChangeHistory =
        integrationChangeHistoryService.getById(id);

    return R.ok().put("integrationChangeHistory", integrationChangeHistory);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:integrationchangehistory:save")
  public R save(@RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {
    integrationChangeHistoryService.save(integrationChangeHistory);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:integrationchangehistory:update")
  public R update(
      @PathVariable("id") Long id,
      @RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {
    integrationChangeHistory.setId(id);
    integrationChangeHistoryService.updateById(integrationChangeHistory);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:integrationchangehistory:delete")
  public R delete(@RequestBody Long[] ids) {
    integrationChangeHistoryService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:integrationchangehistory:delete")
  public R deleteById(@PathVariable("id") Long id) {
    integrationChangeHistoryService.removeById(id);

    return R.ok();
  }
}
