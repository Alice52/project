package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillPromotionEntity;
import ec.coupon.service.SeckillPromotionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 秒杀活动
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/seckillpromotion")
public class SeckillPromotionController extends BaseController {
  @Resource private SeckillPromotionService seckillPromotionService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = seckillPromotionService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  public SeckillPromotionEntity info(@PathVariable("id") Long id) {
    SeckillPromotionEntity seckillPromotion = seckillPromotionService.getById(id);

    return seckillPromotion;
  }

  @PostMapping("/save")
  public void save(@RequestBody SeckillPromotionEntity seckillPromotion) {
    seckillPromotionService.save(seckillPromotion);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody SeckillPromotionEntity seckillPromotion) {
    seckillPromotion.setId(id);
    seckillPromotionService.updateById(seckillPromotion);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    seckillPromotionService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    seckillPromotionService.removeById(id);
  }
}
