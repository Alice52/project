package ec.member.controller;

import ec.common.utils.PageUtils;
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
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return growthChangeHistoryService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public GrowthChangeHistoryEntity info(@PathVariable("id") Long id) {

    return growthChangeHistoryService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody GrowthChangeHistoryEntity growthChangeHistory) {

    growthChangeHistoryService.save(growthChangeHistory);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody GrowthChangeHistoryEntity growthChangeHistory) {

    growthChangeHistory.setId(id);
    growthChangeHistoryService.updateById(growthChangeHistory);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {

    growthChangeHistoryService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {

    growthChangeHistoryService.removeById(id);
  }
}
