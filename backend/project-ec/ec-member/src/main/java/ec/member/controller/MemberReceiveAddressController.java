package ec.member.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.member.entity.MemberReceiveAddressEntity;
import ec.member.service.MemberReceiveAddressService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员收货地址
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Api
@RestController
@RequestMapping("member/memberreceiveaddress")
public class MemberReceiveAddressController {
  @Resource private MemberReceiveAddressService memberReceiveAddressService;

  @GetMapping("/list")
  // @RequiresPermissions("member:memberreceiveaddress:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberReceiveAddressService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("member:memberreceiveaddress:info")
  public R info(@PathVariable("id") Long id) {
    MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(id);

    return R.ok().put("memberReceiveAddress", memberReceiveAddress);
  }

  @PostMapping("/save")
  // @RequiresPermissions("member:memberreceiveaddress:save")
  public R save(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
    memberReceiveAddressService.save(memberReceiveAddress);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("member:memberreceiveaddress:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
    memberReceiveAddress.setId(id);
    memberReceiveAddressService.updateById(memberReceiveAddress);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("member:memberreceiveaddress:delete")
  public R delete(@RequestBody Long[] ids) {
    memberReceiveAddressService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("member:memberreceiveaddress:delete")
  public R deleteById(@PathVariable("id") Long id) {
    memberReceiveAddressService.removeById(id);

    return R.ok();
  }
}
