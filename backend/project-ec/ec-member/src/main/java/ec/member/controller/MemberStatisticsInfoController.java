package ec.member.controller;

import ec.common.utils.PageUtils;
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
  public PageUtils list(@RequestParam Map<String, Object> params) {
    return memberStatisticsInfoService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public MemberStatisticsInfoEntity info(@PathVariable("id") Long id) {
    return memberStatisticsInfoService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody MemberStatisticsInfoEntity memberStatisticsInfo) {
    memberStatisticsInfoService.save(memberStatisticsInfo);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody MemberStatisticsInfoEntity memberStatisticsInfo) {
    memberStatisticsInfo.setId(id);
    memberStatisticsInfoService.updateById(memberStatisticsInfo);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    memberStatisticsInfoService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    memberStatisticsInfoService.removeById(id);
  }
}
