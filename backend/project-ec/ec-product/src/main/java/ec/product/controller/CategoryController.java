package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.CategoryEntity;
import ec.product.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product/category")
public class CategoryController {
  @Resource private CategoryService categoryService;

  @GetMapping("/list")
  // @RequiresPermissions("product:category:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = categoryService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{catId}")
  // @RequiresPermissions("product:category:info")
  public R info(@PathVariable("catId") Long catId) {
    CategoryEntity category = categoryService.getById(catId);

    return R.ok().put("category", category);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:category:save")
  public R save(@RequestBody CategoryEntity category) {
    categoryService.save(category);

    return R.ok();
  }

  @PutMapping("/update/{catId}")
  // @RequiresPermissions("product:category:update")
  public R update(@PathVariable("catId") Long catId, @RequestBody CategoryEntity category) {
    category.setCatId(catId);
    categoryService.updateById(category);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:category:delete")
  public R delete(@RequestBody Long[] catIds) {
    categoryService.removeByIds(Arrays.asList(catIds));

    return R.ok();
  }

  @DeleteMapping("/delete/{catId}")
  // @RequiresPermissions("product:category:delete")
  public R deleteById(@PathVariable("catId") Long catId) {
    categoryService.removeById(catId);

    return R.ok();
  }
}
