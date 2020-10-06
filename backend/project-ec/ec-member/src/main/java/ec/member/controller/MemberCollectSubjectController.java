package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.member.entity.MemberCollectSubjectEntity;
import ec.member.service.MemberCollectSubjectService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员收藏的专题活动
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/membercollectsubject")
public class MemberCollectSubjectController {
  @Resource private MemberCollectSubjectService memberCollectSubjectService;

  @GetMapping("/list")
  // @RequiresPermissions("member:membercollectsubject:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberCollectSubjectService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:membercollectsubject:info")
  public R info(@PathVariable("id") Long id) {
    MemberCollectSubjectEntity memberCollectSubject = memberCollectSubjectService.getById(id);

    return R.ok().put("memberCollectSubject", memberCollectSubject);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:membercollectsubject:save")
  public R save(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
    memberCollectSubjectService.save(memberCollectSubject);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:membercollectsubject:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody MemberCollectSubjectEntity memberCollectSubject) {
    memberCollectSubject.setId(id);
    memberCollectSubjectService.updateById(memberCollectSubject);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:membercollectsubject:delete")
  public R delete(@RequestBody Long[] ids) {
    memberCollectSubjectService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:membercollectsubject:delete")
  public R deleteById(@PathVariable("id") Long id) {
    memberCollectSubjectService.removeById(id);

    return R.ok();
  }
}
