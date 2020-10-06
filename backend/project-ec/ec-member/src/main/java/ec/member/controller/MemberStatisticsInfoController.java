package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.member.entity.MemberStatisticsInfoEntity;
import ec.member.service.MemberStatisticsInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员统计信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/memberstatisticsinfo")
public class MemberStatisticsInfoController {
  @Resource private MemberStatisticsInfoService memberStatisticsInfoService;

  @GetMapping("/list")
  // @RequiresPermissions("member:memberstatisticsinfo:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberStatisticsInfoService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:memberstatisticsinfo:info")
  public R info(@PathVariable("id") Long id) {
    MemberStatisticsInfoEntity memberStatisticsInfo = memberStatisticsInfoService.getById(id);

    return R.ok().put("memberStatisticsInfo", memberStatisticsInfo);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:memberstatisticsinfo:save")
  public R save(@RequestBody MemberStatisticsInfoEntity memberStatisticsInfo) {
    memberStatisticsInfoService.save(memberStatisticsInfo);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:memberstatisticsinfo:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody MemberStatisticsInfoEntity memberStatisticsInfo) {
    memberStatisticsInfo.setId(id);
    memberStatisticsInfoService.updateById(memberStatisticsInfo);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:memberstatisticsinfo:delete")
  public R delete(@RequestBody Long[] ids) {
    memberStatisticsInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:memberstatisticsinfo:delete")
  public R deleteById(@PathVariable("id") Long id) {
    memberStatisticsInfoService.removeById(id);

    return R.ok();
  }
}
