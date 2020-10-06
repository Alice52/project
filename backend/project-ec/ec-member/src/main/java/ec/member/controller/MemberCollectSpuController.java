package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
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
  // @RequiresPermissions("member:membercollectspu:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberCollectSpuService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:membercollectspu:info")
  public R info(@PathVariable("id") Long id) {
    MemberCollectSpuEntity memberCollectSpu = memberCollectSpuService.getById(id);

    return R.ok().put("memberCollectSpu", memberCollectSpu);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:membercollectspu:save")
  public R save(@RequestBody MemberCollectSpuEntity memberCollectSpu) {
    memberCollectSpuService.save(memberCollectSpu);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:membercollectspu:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody MemberCollectSpuEntity memberCollectSpu) {
    memberCollectSpu.setId(id);
    memberCollectSpuService.updateById(memberCollectSpu);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:membercollectspu:delete")
  public R delete(@RequestBody Long[] ids) {
    memberCollectSpuService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:membercollectspu:delete")
  public R deleteById(@PathVariable("id") Long id) {
    memberCollectSpuService.removeById(id);

    return R.ok();
  }
}
