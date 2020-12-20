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
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return integrationChangeHistoryService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public IntegrationChangeHistoryEntity info(@PathVariable("id") Long id) {

    return integrationChangeHistoryService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {

    integrationChangeHistoryService.save(integrationChangeHistory);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id,
      @RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {
    integrationChangeHistory.setId(id);
    integrationChangeHistoryService.updateById(integrationChangeHistory);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    integrationChangeHistoryService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public R deleteById(@PathVariable("id") Long id) {
    integrationChangeHistoryService.removeById(id);

    return R.ok();
  }
}
