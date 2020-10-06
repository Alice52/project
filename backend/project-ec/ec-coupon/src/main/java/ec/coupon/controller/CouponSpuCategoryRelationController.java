package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponSpuCategoryRelationEntity;
import ec.coupon.service.CouponSpuCategoryRelationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 优惠券分类关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:25
 */
@Api
@RestController
@RequestMapping("coupon/couponspucategoryrelation")
public class CouponSpuCategoryRelationController extends BaseController {
  @Resource private CouponSpuCategoryRelationService couponSpuCategoryRelationService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:couponspucategoryrelation:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = couponSpuCategoryRelationService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:couponspucategoryrelation:info")
  public CouponSpuCategoryRelationEntity info(@PathVariable("id") Long id) {
    CouponSpuCategoryRelationEntity couponSpuCategoryRelation =
        couponSpuCategoryRelationService.getById(id);

    return couponSpuCategoryRelation;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:couponspucategoryrelation:save")
  public void save(@RequestBody CouponSpuCategoryRelationEntity couponSpuCategoryRelation) {
    couponSpuCategoryRelationService.save(couponSpuCategoryRelation);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:couponspucategoryrelation:update")
  public void update(
      @PathVariable("id") Long id,
      @RequestBody CouponSpuCategoryRelationEntity couponSpuCategoryRelation) {
    couponSpuCategoryRelation.setId(id);
    couponSpuCategoryRelationService.updateById(couponSpuCategoryRelation);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:couponspucategoryrelation:delete")
  public void delete(@RequestBody Long[] ids) {
    couponSpuCategoryRelationService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:couponspucategoryrelation:delete")
  public void deleteById(@PathVariable("id") Long id) {
    couponSpuCategoryRelationService.removeById(id);
  }
}
