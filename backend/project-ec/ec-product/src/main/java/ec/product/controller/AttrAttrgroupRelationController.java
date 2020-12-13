package ec.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.AttrAttrgroupRelationEntity;
import ec.product.model.vo.AttrVO;
import ec.product.service.AttrAttrgroupRelationService;
import ec.product.service.AttrService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.CheckForNull;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
  @Resource private AttrService attrService;

  @GetMapping("/attr-attrgroup-relations")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = attrAttrgroupRelationService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/attrgroup-attrs/{groupId}")
  public R listAttrs(@PathVariable("groupId") Long groupId) {

    List<AttrVO> vos = attrService.getByGroupId(groupId);

    return R.ok().put("data", vos);
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

  @PutMapping("/attr-attrgroup-relations/{groupId}")
  public R addRelations(
      @NotNull @PathVariable("groupId") Long groupId, @NotNull @RequestBody List<Long> attrIds) {

    attrAttrgroupRelationService.addRelations(groupId, attrIds);
    return R.ok();
  }

  @DeleteMapping("/attr-attrgroup-relations-by-attrIds")
  public R deleteByAttrIds(@RequestBody Long[] ids) {
    attrAttrgroupRelationService.deleteByAttrIds(ids);

    return R.ok();
  }

  @DeleteMapping("/attr-attrgroup-relations")
  public R delete(@RequestBody Long[] ids) {
    // TODO: remove relative data
    attrAttrgroupRelationService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/attr-attrgroup-relation")
  public R deleteById(@PathVariable("id") Long id) {
    // TODO: remove relative data
    attrAttrgroupRelationService.removeById(id);

    return R.ok();
  }
}
