package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.CategoryBrandRelationEntity;
import ec.product.service.CategoryBrandRelationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
  @Resource private CategoryBrandRelationService categoryBrandRelationService;

  @GetMapping("/list")
  // @RequiresPermissions("product:categorybrandrelation:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = categoryBrandRelationService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:categorybrandrelation:info")
  public R info(@PathVariable("id") Long id) {
    CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

    return R.ok().put("categoryBrandRelation", categoryBrandRelation);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:categorybrandrelation:save")
  public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    categoryBrandRelationService.save(categoryBrandRelation);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:categorybrandrelation:update")
  public R update(
      @PathVariable("id") Long id, @RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    categoryBrandRelation.setId(id);
    categoryBrandRelationService.updateById(categoryBrandRelation);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:categorybrandrelation:delete")
  public R delete(@RequestBody Long[] ids) {
    categoryBrandRelationService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:categorybrandrelation:delete")
  public R deleteById(@PathVariable("id") Long id) {
    categoryBrandRelationService.removeById(id);

    return R.ok();
  }
}
