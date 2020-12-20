package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.CouponSpuRelationEntity;
import ec.coupon.service.CouponSpuRelationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/couponspurelation")
public class CouponSpuRelationController extends BaseController {
  @Resource private CouponSpuRelationService couponSpuRelationService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = couponSpuRelationService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  public CouponSpuRelationEntity info(@PathVariable("id") Long id) {
    CouponSpuRelationEntity couponSpuRelation = couponSpuRelationService.getById(id);

    return couponSpuRelation;
  }

  @PostMapping("/save")
  public void save(@RequestBody CouponSpuRelationEntity couponSpuRelation) {
    couponSpuRelationService.save(couponSpuRelation);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody CouponSpuRelationEntity couponSpuRelation) {
    couponSpuRelation.setId(id);
    couponSpuRelationService.updateById(couponSpuRelation);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    couponSpuRelationService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    couponSpuRelationService.removeById(id);
  }
}
