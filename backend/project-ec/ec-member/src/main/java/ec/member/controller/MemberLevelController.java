package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.member.entity.MemberLevelEntity;
import ec.member.service.MemberLevelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员等级
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/memberlevel")
public class MemberLevelController {
  @Resource private MemberLevelService memberLevelService;

  @GetMapping("/list")
  // @RequiresPermissions("member:memberlevel:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberLevelService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:memberlevel:info")
  public R info(@PathVariable("id") Long id) {
    MemberLevelEntity memberLevel = memberLevelService.getById(id);

    return R.ok().put("memberLevel", memberLevel);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:memberlevel:save")
  public R save(@RequestBody MemberLevelEntity memberLevel) {
    memberLevelService.save(memberLevel);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:memberlevel:update")
  public R update(@PathVariable("id") Long id, @RequestBody MemberLevelEntity memberLevel) {
    memberLevel.setId(id);
    memberLevelService.updateById(memberLevel);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:memberlevel:delete")
  public R delete(@RequestBody Long[] ids) {
    memberLevelService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:memberlevel:delete")
  public R deleteById(@PathVariable("id") Long id) {
    memberLevelService.removeById(id);

    return R.ok();
  }
}
