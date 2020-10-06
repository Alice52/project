package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
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
  // @RequiresPermissions("member:memberloginlog:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberLoginLogService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:memberloginlog:info")
  public R info(@PathVariable("id") Long id) {
    MemberLoginLogEntity memberLoginLog = memberLoginLogService.getById(id);

    return R.ok().put("memberLoginLog", memberLoginLog);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:memberloginlog:save")
  public R save(@RequestBody MemberLoginLogEntity memberLoginLog) {
    memberLoginLogService.save(memberLoginLog);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:memberloginlog:update")
  public R update(@PathVariable("id") Long id, @RequestBody MemberLoginLogEntity memberLoginLog) {
    memberLoginLog.setId(id);
    memberLoginLogService.updateById(memberLoginLog);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:memberloginlog:delete")
  public R delete(@RequestBody Long[] ids) {
    memberLoginLogService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:memberloginlog:delete")
  public R deleteById(@PathVariable("id") Long id) {
    memberLoginLogService.removeById(id);

    return R.ok();
  }
}
