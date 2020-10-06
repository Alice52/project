package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillSkuRelationEntity;
import ec.coupon.service.SeckillSkuRelationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/seckillskurelation")
public class SeckillSkuRelationController extends BaseController {
  @Resource private SeckillSkuRelationService seckillSkuRelationService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:seckillskurelation:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = seckillSkuRelationService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:seckillskurelation:info")
  public SeckillSkuRelationEntity info(@PathVariable("id") Long id) {
    SeckillSkuRelationEntity seckillSkuRelation = seckillSkuRelationService.getById(id);

    return seckillSkuRelation;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:seckillskurelation:save")
  public void save(@RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
    seckillSkuRelationService.save(seckillSkuRelation);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:seckillskurelation:update")
  public void update(
      @PathVariable("id") Long id, @RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
    seckillSkuRelation.setId(id);
    seckillSkuRelationService.updateById(seckillSkuRelation);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:seckillskurelation:delete")
  public void delete(@RequestBody Long[] ids) {
    seckillSkuRelationService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:seckillskurelation:delete")
  public void deleteById(@PathVariable("id") Long id) {
    seckillSkuRelationService.removeById(id);
  }
}
