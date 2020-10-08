package ec.product.controller;

import ec.common.annotation.AddGroup;
import ec.common.annotation.UpdateGroup;
import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.model.AttrGroupVO;
import ec.product.service.AttrGroupService;
import ec.product.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.Map;

import static ec.product.converter.AttrGroupConverter.INSTANCE;
/**
 * 属性分组
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product")
public class AttrGroupController {

  @Resource private CategoryService categoryService;
  @Resource private AttrGroupService attrGroupService;

  @GetMapping("/attr-groups/{catId}")
  public R list(@RequestParam Map<String, Object> params, @PathVariable("catId") Long catId) {
    PageUtils page = attrGroupService.queryPage(params, catId);

    return R.ok().put("page", page);
  }

  @GetMapping("/attr-group/{attrGroupId}")
  public R info(@PathVariable("attrGroupId") Long attrGroupId) {
    AttrGroupVO attrGroupVO = INSTANCE.po2vo(attrGroupService.getById(attrGroupId));
    attrGroupVO.setCatelogPath(categoryService.findCatelogPath(attrGroupVO.getCatelogId()));

    return R.ok().put("attrGroup", attrGroupVO);
  }

  @PostMapping(value = {"/attr-group", "/attr-groups"})
  public R save(@RequestBody @Validated({AddGroup.class, Default.class}) AttrGroupVO attrGroupVO) {
    attrGroupService.save(INSTANCE.vo2po(attrGroupVO));

    return R.ok();
  }

  @PutMapping("/attr-group/{attrGroupId}")
  public R update(
      @PathVariable("attrGroupId") Long attrGroupId,
      @RequestBody @Validated({UpdateGroup.class, Default.class}) AttrGroupVO attrGroupVO) {
    attrGroupVO.setAttrGroupId(attrGroupId);
    attrGroupService.updateById(INSTANCE.vo2po(attrGroupVO));

    return R.ok();
  }

  @DeleteMapping("/attr-group")
  public R delete(@RequestBody Long[] attrGroupIds) {
    attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

    return R.ok();
  }

  @DeleteMapping("/attr-groups")
  public R deleteById(@PathVariable("attrGroupId") Long attrGroupId) {
    attrGroupService.removeById(attrGroupId);

    return R.ok();
  }
}
