package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.member.entity.MemberLevelEntity;
import ec.member.model.vo.MemberLevelVO;
import ec.member.service.MemberLevelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

import static ec.member.converter.MemberLevelConverter.INSTANCE;

/**
 * 会员等级
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member")
public class MemberLevelController {
  @Resource private MemberLevelService memberLevelService;

  @GetMapping("/member-levels")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return memberLevelService.queryPage(params);
  }

  @GetMapping("/member-level/{id}")
  public MemberLevelVO info(@PathVariable("id") Long id) {
    MemberLevelEntity memberLevel = memberLevelService.getById(id);

    return INSTANCE.po2vo(memberLevel);
  }

  @PostMapping("/member-level")
  public void save(@RequestBody MemberLevelVO memberLevelVO) {
    MemberLevelEntity entity = INSTANCE.vo2po(memberLevelVO);
    memberLevelService.save(entity);
  }

  @PutMapping("/member-level/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody MemberLevelVO vo) {
    vo.setId(id);
    memberLevelService.updateById(INSTANCE.vo2po(vo));
  }

  @DeleteMapping("/member-level")
  public void delete(@RequestBody Long[] ids) {
    memberLevelService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/member-level/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    memberLevelService.removeById(id);
  }
}
