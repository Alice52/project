package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SpuBoundsEntity;
import ec.coupon.model.to.SpuBoundTO;
import ec.coupon.service.SpuBoundsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

import static ec.coupon.converter.SpuBoundConverter.INSTANCE;

/**
 * 商品spu积分设置
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("/coupon")
public class SpuBoundsController extends BaseController {
  @Resource private SpuBoundsService spuBoundsService;

  @GetMapping("/spu-bounds")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuBoundsService.queryPage(params);

    return page;
  }

  @GetMapping("/spu-bounds/{id}")
  public SpuBoundsEntity info(@PathVariable("id") Long id) {
    SpuBoundsEntity spuBounds = spuBoundsService.getById(id);

    return spuBounds;
  }

  @PostMapping("/spu-bounds")
  public void save(@RequestBody SpuBoundTO to) {

    spuBoundsService.save(INSTANCE.to2po(to));
  }

  @PutMapping("/spu-bounds/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody SpuBoundsEntity spuBounds) {
    spuBounds.setId(id);
    spuBoundsService.updateById(spuBounds);
  }

  @DeleteMapping("/spu-bounds")
  public void delete(@RequestBody Long[] ids) {
    spuBoundsService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/spu-bounds/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    spuBoundsService.removeById(id);
  }
}
