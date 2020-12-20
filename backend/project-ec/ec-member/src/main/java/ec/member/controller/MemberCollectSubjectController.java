package ec.member.controller;

import ec.common.utils.PageUtils;
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
  public PageUtils list(@RequestParam Map<String, Object> params) {
    return memberCollectSubjectService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public MemberCollectSubjectEntity info(@PathVariable("id") Long id) {

    return memberCollectSubjectService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
    memberCollectSubjectService.save(memberCollectSubject);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody MemberCollectSubjectEntity memberCollectSubject) {
    memberCollectSubject.setId(id);
    memberCollectSubjectService.updateById(memberCollectSubject);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    memberCollectSubjectService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    memberCollectSubjectService.removeById(id);
  }
}
