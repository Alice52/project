package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.AttrAttrgroupRelationEntity;
import ec.product.service.AttrAttrgroupRelationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product")
public class AttrAttrgroupRelationController {
  @Resource private AttrAttrgroupRelationService attrAttrgroupRelationService;

  @GetMapping("/attr-attrgroup-relations")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = attrAttrgroupRelationService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/attr-attrgroup-relation/{id}")
  public R info(@PathVariable("id") Long id) {
    AttrAttrgroupRelationEntity attrAttrgroupRelation = attrAttrgroupRelationService.getById(id);

    return R.ok().put("attrAttrgroupRelation", attrAttrgroupRelation);
  }

  @PostMapping(value = {"/attr-attrgroup-relation", "/attr-attrgroup-relations"})
  public R save(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation) {
    attrAttrgroupRelationService.save(attrAttrgroupRelation);

    return R.ok();
  }

  @PutMapping("/attr-attrgroup-relation/{id}")
  public R update(
      @PathVariable("id") Long id, @RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation) {
    attrAttrgroupRelation.setId(id);
    attrAttrgroupRelationService.updateById(attrAttrgroupRelation);

    return R.ok();
  }

  @DeleteMapping("/attr-attrgroup-relations")
  public R delete(@RequestBody Long[] ids) {
    attrAttrgroupRelationService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/attr-attrgroup-relation")
  public R deleteById(@PathVariable("id") Long id) {
    attrAttrgroupRelationService.removeById(id);

    return R.ok();
  }
}
