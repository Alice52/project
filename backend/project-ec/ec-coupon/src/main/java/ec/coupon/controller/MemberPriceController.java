package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.MemberPriceEntity;
import ec.coupon.service.MemberPriceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品会员价格
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/memberprice")
public class MemberPriceController extends BaseController {
  @Resource private MemberPriceService memberPriceService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:memberprice:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberPriceService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:memberprice:info")
  public MemberPriceEntity info(@PathVariable("id") Long id) {
    MemberPriceEntity memberPrice = memberPriceService.getById(id);

    return memberPrice;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:memberprice:save")
  public void save(@RequestBody MemberPriceEntity memberPrice) {
    memberPriceService.save(memberPrice);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:memberprice:update")
  public void update(@PathVariable("id") Long id, @RequestBody MemberPriceEntity memberPrice) {
    memberPrice.setId(id);
    memberPriceService.updateById(memberPrice);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:memberprice:delete")
  public void delete(@RequestBody Long[] ids) {
    memberPriceService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:memberprice:delete")
  public void deleteById(@PathVariable("id") Long id) {
    memberPriceService.removeById(id);
  }
}
