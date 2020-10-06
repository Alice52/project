package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponEntity;
import ec.coupon.service.CouponService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 优惠券信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:25
 */
@Api
@RestController
@RequestMapping("coupon/coupon")
public class CouponController extends BaseController {
  @Resource private CouponService couponService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:coupon:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = couponService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:coupon:info")
  public CouponEntity info(@PathVariable("id") Long id) {
    CouponEntity coupon = couponService.getById(id);

    return coupon;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:coupon:save")
  public void save(@RequestBody CouponEntity coupon) {
    couponService.save(coupon);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:coupon:update")
  public void update(@PathVariable("id") Long id, @RequestBody CouponEntity coupon) {
    coupon.setId(id);
    couponService.updateById(coupon);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:coupon:delete")
  public void delete(@RequestBody Long[] ids) {
    couponService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:coupon:delete")
  public void deleteById(@PathVariable("id") Long id) {
    couponService.removeById(id);
  }
}
