package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.converter.SkuFullReductionConverter;
import ec.coupon.entity.SkuFullReductionEntity;
import ec.coupon.model.to.SkuReductionTO;
import ec.coupon.service.SkuFullReductionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;

import static ec.coupon.converter.SkuFullReductionConverter.INSTANCE;

/**
 * 商品满减信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("/coupon")
public class SkuFullReductionController extends BaseController {
  @Resource private SkuFullReductionService skuFullReductionService;

  @GetMapping("/sku-reductions")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = skuFullReductionService.queryPage(params);

    return page;
  }

  @GetMapping("/sku-reduction/{id}")
  public SkuFullReductionEntity info(@PathVariable("id") Long id) {
    SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);

    return skuFullReduction;
  }

  @PostMapping("/sku-reduction")
  public void save(@RequestBody @NotNull SkuReductionTO to) {

    skuFullReductionService.saveSkuReduction(to);
  }

  @PutMapping("/sku-reduction/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody SkuFullReductionEntity skuFullReduction) {
    skuFullReduction.setId(id);
    skuFullReductionService.updateById(skuFullReduction);
  }

  @DeleteMapping("/sku-reduction")
  public void delete(@RequestBody Long[] ids) {
    skuFullReductionService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/sku-reduction/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    skuFullReductionService.removeById(id);
  }
}
