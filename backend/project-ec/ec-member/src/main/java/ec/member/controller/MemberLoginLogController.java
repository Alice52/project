package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.member.entity.MemberLoginLogEntity;
import ec.member.service.MemberLoginLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员登录记录
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/memberloginlog")
public class MemberLoginLogController {
  @Resource private MemberLoginLogService memberLoginLogService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    return memberLoginLogService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public MemberLoginLogEntity info(@PathVariable("id") Long id) {
    return memberLoginLogService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody MemberLoginLogEntity memberLoginLog) {
    memberLoginLogService.save(memberLoginLog);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody MemberLoginLogEntity memberLoginLog) {
    memberLoginLog.setId(id);
    memberLoginLogService.updateById(memberLoginLog);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    memberLoginLogService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    memberLoginLogService.removeById(id);
  }
}
