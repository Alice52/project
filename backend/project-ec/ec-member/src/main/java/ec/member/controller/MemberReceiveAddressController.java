package ec.member.controller;

import ec.common.utils.PageUtils;
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
  public PageUtils list(@RequestParam Map<String, Object> params) {
    return memberReceiveAddressService.queryPage(params);
  }

  @GetMapping("/info/{id}")
  public MemberReceiveAddressEntity info(@PathVariable("id") Long id) {
    return memberReceiveAddressService.getById(id);
  }

  @PostMapping("/save")
  public void save(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
    memberReceiveAddressService.save(memberReceiveAddress);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
    memberReceiveAddress.setId(id);
    memberReceiveAddressService.updateById(memberReceiveAddress);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    memberReceiveAddressService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    memberReceiveAddressService.removeById(id);
  }
}
