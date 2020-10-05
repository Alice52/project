package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.AttrEntity;
import ec.product.service.AttrService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品属性
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product/attr")
public class AttrController {
  @Resource private AttrService attrService;

  @GetMapping("/list")
  // @RequiresPermissions("product:attr:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = attrService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{attrId}")
  // @RequiresPermissions("product:attr:info")
  public R info(@PathVariable("attrId") Long attrId) {
    AttrEntity attr = attrService.getById(attrId);

    return R.ok().put("attr", attr);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:attr:save")
  public R save(@RequestBody AttrEntity attr) {
    attrService.save(attr);

    return R.ok();
  }

  @PutMapping("/update/{attrId}")
  // @RequiresPermissions("product:attr:update")
  public R update(@PathVariable("attrId") Long attrId, @RequestBody AttrEntity attr) {
    attr.setAttrId(attrId);
    attrService.updateById(attr);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:attr:delete")
  public R delete(@RequestBody Long[] attrIds) {
    attrService.removeByIds(Arrays.asList(attrIds));

    return R.ok();
  }

  @DeleteMapping("/delete/{attrId}")
  // @RequiresPermissions("product:attr:delete")
  public R deleteById(@PathVariable("attrId") Long attrId) {
    attrService.removeById(attrId);

    return R.ok();
  }
}
