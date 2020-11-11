package ec.product.controller;

import ec.common.annotation.AddGroup;
import ec.common.annotation.UpdateGroup;
import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.CategoryBrandRelationEntity;
import ec.product.model.vo.CategoryBrandRelationVO;
import ec.product.service.CategoryBrandRelationService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ec.product.converter.CategoryBrandRelationConverter.INSTANCE;
/**
 * 品牌分类关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product")
public class CategoryBrandRelationController {
  @Resource private CategoryBrandRelationService categoryBrandRelationService;

  @GetMapping("/category-brand-relations")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = categoryBrandRelationService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/brand-categories/{brandId}")
  public R listByBrandId(@PathVariable("brandId") Long brandId) {

    List<CategoryBrandRelationVO> relationVOS = categoryBrandRelationService.getbyBrandId(brandId);

    return R.ok().put("relations", relationVOS);
  }

  @GetMapping("/category-brand-relation/{id}")
  public R info(@PathVariable("id") Long id) {
    CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

    return R.ok().put("categoryBrandRelation", categoryBrandRelation);
  }

  @PostMapping(value = {"/category-brand-relation", "/category-brand-relations"})
  public R save(
      @RequestBody @Validated(value = {AddGroup.class, Default.class})
          CategoryBrandRelationVO categoryBrandRelationVO) {
    CategoryBrandRelationEntity entity = INSTANCE.vo2po(categoryBrandRelationVO);

    categoryBrandRelationService.save(entity);

    return R.ok();
  }

  @PutMapping("/category-brand-relation/{id}")
  public R update(
      @PathVariable("id") Long id,
      @RequestBody @Validated(value = {UpdateGroup.class, Default.class})
          CategoryBrandRelationVO categoryBrandRelationVO) {
    categoryBrandRelationVO.setId(id);
    categoryBrandRelationService.updateById(INSTANCE.vo2po(categoryBrandRelationVO));

    return R.ok();
  }

  @DeleteMapping("/category-brand-relations")
  // @RequiresPermissions("product:categorybrandrelation:delete")
  public R delete(@RequestBody Long[] ids) {
    categoryBrandRelationService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/category-brand-relation/{id}")
  // @RequiresPermissions("product:categorybrandrelation:delete")
  public R deleteById(@PathVariable("id") Long id) {
    categoryBrandRelationService.removeById(id);

    return R.ok();
  }
}
