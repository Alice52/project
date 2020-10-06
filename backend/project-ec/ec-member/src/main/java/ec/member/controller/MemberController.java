package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.member.entity.MemberEntity;
import ec.member.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/member")
public class MemberController {
  @Resource private MemberService memberService;

  @GetMapping("/list")
  // @RequiresPermissions("member:member:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:member:info")
  public R info(@PathVariable("id") Long id) {
    MemberEntity member = memberService.getById(id);

    return R.ok().put("member", member);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:member:save")
  public R save(@RequestBody MemberEntity member) {
    memberService.save(member);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:member:update")
  public R update(@PathVariable("id") Long id, @RequestBody MemberEntity member) {
    member.setId(id);
    memberService.updateById(member);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:member:delete")
  public R delete(@RequestBody Long[] ids) {
    memberService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:member:delete")
  public R deleteById(@PathVariable("id") Long id) {
    memberService.removeById(id);

    return R.ok();
  }
}
