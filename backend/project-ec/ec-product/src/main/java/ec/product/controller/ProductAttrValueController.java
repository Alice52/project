package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.ProductAttrValueEntity;
import ec.product.service.ProductAttrValueService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * spu属性值
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product/productattrvalue")
public class ProductAttrValueController {
  @Resource private ProductAttrValueService productAttrValueService;

  @GetMapping("/list")
  // @RequiresPermissions("product:productattrvalue:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = productAttrValueService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:productattrvalue:info")
  public R info(@PathVariable("id") Long id) {
    ProductAttrValueEntity productAttrValue = productAttrValueService.getById(id);

    return R.ok().put("productAttrValue", productAttrValue);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:productattrvalue:save")
  public R save(@RequestBody ProductAttrValueEntity productAttrValue) {
    productAttrValueService.save(productAttrValue);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:productattrvalue:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody ProductAttrValueEntity productAttrValue) {
    productAttrValue.setId(id);
    productAttrValueService.updateById(productAttrValue);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:productattrvalue:delete")
  public R delete(@RequestBody Long[] ids) {
    productAttrValueService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:productattrvalue:delete")
  public R deleteById(@PathVariable("id") Long id) {
    productAttrValueService.removeById(id);

    return R.ok();
  }
}
