package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.AttrGroupEntity;
import ec.product.service.AttrGroupService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 属性分组
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
  @Resource private AttrGroupService attrGroupService;

  @GetMapping("/list")
  // @RequiresPermissions("product:attrgroup:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = attrGroupService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{attrGroupId}")
  // @RequiresPermissions("product:attrgroup:info")
  public R info(@PathVariable("attrGroupId") Long attrGroupId) {
    AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

    return R.ok().put("attrGroup", attrGroup);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:attrgroup:save")
  public R save(@RequestBody AttrGroupEntity attrGroup) {
    attrGroupService.save(attrGroup);

    return R.ok();
  }

  @PutMapping("/update/{attrGroupId}")
  // @RequiresPermissions("product:attrgroup:update")
  public R update(
      @PathVariable("attrGroupId") Long attrGroupId, @RequestBody AttrGroupEntity attrGroup) {
    attrGroup.setAttrGroupId(attrGroupId);
    attrGroupService.updateById(attrGroup);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:attrgroup:delete")
  public R delete(@RequestBody Long[] attrGroupIds) {
    attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

    return R.ok();
  }

  @DeleteMapping("/delete/{attrGroupId}")
  // @RequiresPermissions("product:attrgroup:delete")
  public R deleteById(@PathVariable("attrGroupId") Long attrGroupId) {
    attrGroupService.removeById(attrGroupId);

    return R.ok();
  }
}
