package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponHistoryEntity;
import ec.coupon.service.CouponHistoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:25
 */
@Api
@RestController
@RequestMapping("coupon/couponhistory")
public class CouponHistoryController extends BaseController {
  @Resource private CouponHistoryService couponHistoryService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:couponhistory:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = couponHistoryService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:couponhistory:info")
  public CouponHistoryEntity info(@PathVariable("id") Long id) {
    CouponHistoryEntity couponHistory = couponHistoryService.getById(id);

    return couponHistory;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:couponhistory:save")
  public void save(@RequestBody CouponHistoryEntity couponHistory) {
    couponHistoryService.save(couponHistory);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:couponhistory:update")
  public void update(@PathVariable("id") Long id, @RequestBody CouponHistoryEntity couponHistory) {
    couponHistory.setId(id);
    couponHistoryService.updateById(couponHistory);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:couponhistory:delete")
  public void delete(@RequestBody Long[] ids) {
    couponHistoryService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:couponhistory:delete")
  public void deleteById(@PathVariable("id") Long id) {
    couponHistoryService.removeById(id);
  }
}
