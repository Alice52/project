package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillSkuNoticeEntity;
import ec.coupon.service.SeckillSkuNoticeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 秒杀商品通知订阅
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/seckillskunotice")
public class SeckillSkuNoticeController extends BaseController {
  @Resource private SeckillSkuNoticeService seckillSkuNoticeService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:seckillskunotice:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = seckillSkuNoticeService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:seckillskunotice:info")
  public SeckillSkuNoticeEntity info(@PathVariable("id") Long id) {
    SeckillSkuNoticeEntity seckillSkuNotice = seckillSkuNoticeService.getById(id);

    return seckillSkuNotice;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:seckillskunotice:save")
  public void save(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice) {
    seckillSkuNoticeService.save(seckillSkuNotice);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:seckillskunotice:update")
  public void update(
      @PathVariable("id") Long id, @RequestBody SeckillSkuNoticeEntity seckillSkuNotice) {
    seckillSkuNotice.setId(id);
    seckillSkuNoticeService.updateById(seckillSkuNotice);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:seckillskunotice:delete")
  public void delete(@RequestBody Long[] ids) {
    seckillSkuNoticeService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:seckillskunotice:delete")
  public void deleteById(@PathVariable("id") Long id) {
    seckillSkuNoticeService.removeById(id);
  }
}
