package ec.member.controller;

import ec.common.utils.PageUtils;
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
  public PageUtils list(@RequestParam Map<String, Object> params) {
    return memberService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public MemberEntity info(@PathVariable("id") Long id) {
    return memberService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody MemberEntity member) {
    memberService.save(member);
  }

  @PutMapping("/update/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody MemberEntity member) {
    member.setId(id);
    memberService.updateById(member);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    memberService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    memberService.removeById(id);
  }
}
