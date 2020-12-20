package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SkuLadderEntity;
import ec.coupon.service.SkuLadderService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/skuladder")
public class SkuLadderController extends BaseController {
  @Resource private SkuLadderService skuLadderService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = skuLadderService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  public SkuLadderEntity info(@PathVariable("id") Long id) {
    SkuLadderEntity skuLadder = skuLadderService.getById(id);

    return skuLadder;
  }

  @PostMapping("/save")
  public void save(@RequestBody SkuLadderEntity skuLadder) {
    skuLadderService.save(skuLadder);
  }

  @PutMapping("/update/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody SkuLadderEntity skuLadder) {
    skuLadder.setId(id);
    skuLadderService.updateById(skuLadder);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    skuLadderService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    skuLadderService.removeById(id);
  }
}
