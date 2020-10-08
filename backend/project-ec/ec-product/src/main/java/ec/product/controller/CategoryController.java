package ec.product.controller;

import ec.common.annotation.AddGroup;
import ec.common.annotation.UpdateGroup;
import ec.common.utils.R;
import ec.product.model.CategoryEntityVO;
import ec.product.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.List;

import static ec.product.converter.CategoryEntityConverter.INSTANCE;

/**
 * Goods category
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

  /**
   * Query all category organized by tree model.
   *
   * @return
   */
  @GetMapping("/list/tree")
  public R list() {

    return R.ok().put("data", categoryService.listWithTree());
  }

  @GetMapping("/info/{catId}")
  // @RequiresPermissions("product:category:info")
  public R info(@PathVariable("catId") Long catId) {

    return R.ok().put("category", INSTANCE.po2vo(categoryService.getById(catId)));
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:category:save")
  public R save(
      @RequestBody @Validated({Default.class, AddGroup.class}) CategoryEntityVO categoryEntityVO) {
    categoryService.save(INSTANCE.vo2po(categoryEntityVO));

    return R.ok();
  }

  @PutMapping("/update/{catId}")
  // @RequiresPermissions("product:category:update")
  public R update(
      @PathVariable("catId") Long catId,
      @RequestBody @Validated({Default.class, UpdateGroup.class})
          CategoryEntityVO categoryEntityVO) {

    categoryEntityVO.setCatId(catId);
    categoryService.updateById(INSTANCE.vo2po(categoryEntityVO));

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:category:delete")
  public R delete(@RequestBody List<Long> catIds) {
    // categoryService.removeByIds(catIds);
    categoryService.removeMenuByIds(catIds);
    return R.ok();
  }

  @DeleteMapping("/delete/{catId}")
  // @RequiresPermissions("product:category:delete")
  public R deleteById(@PathVariable("catId") Long catId) {
    categoryService.removeById(catId);

    return R.ok();
  }
}
