package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.member.entity.MemberCollectSpuEntity;
import ec.member.service.MemberCollectSpuService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/membercollectspu")
public class MemberCollectSpuController {
  @Resource private MemberCollectSpuService memberCollectSpuService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return memberCollectSpuService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public MemberCollectSpuEntity info(@PathVariable("id") Long id) {

    return memberCollectSpuService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody MemberCollectSpuEntity memberCollectSpu) {
    memberCollectSpuService.save(memberCollectSpu);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody MemberCollectSpuEntity memberCollectSpu) {
    memberCollectSpu.setId(id);
    memberCollectSpuService.updateById(memberCollectSpu);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    memberCollectSpuService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    memberCollectSpuService.removeById(id);
  }
}
