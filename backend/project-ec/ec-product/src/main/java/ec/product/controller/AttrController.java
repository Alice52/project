package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.model.vo.AttrEntityVO;
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
@RequestMapping("/product")
public class AttrController {
  @Resource private AttrService attrService;

  @GetMapping(value = "/attrs/{attrtype}/{catId}")
  public R list(
      @PathVariable("attrtype") boolean attrtype,
      @PathVariable("catId") long catId,
      @RequestParam Map<String, Object> params) {

    PageUtils page = attrService.queryPage(params, attrtype, catId);

    return R.ok().put("page", page);
  }

  @GetMapping("/attr/{attrId}")
  public R info(@PathVariable("attrId") Long attrId) {

    AttrEntityVO attr = attrService.getAttrInfo(attrId);

    return R.ok().put("attr", attr);
  }

  @PostMapping(value = {"/attr", "/attrs"})
  public R save(@RequestBody AttrEntityVO vo) {

    return R.ok().put("success", attrService.saveAttr(vo));
  }

  @PutMapping("/attr/{attrId}")
  public R update(@PathVariable("attrId") Long attrId, @RequestBody AttrEntityVO vo) {
    vo.setAttrId(attrId);

    return R.ok().put("success", attrService.updateAttr(vo));
  }

  @DeleteMapping("/attrs")
  public R delete(@RequestBody Long[] attrIds) {
    attrService.removeByIds(Arrays.asList(attrIds));

    return R.ok();
  }

  @DeleteMapping("/attr/{attrId}")
  public R deleteById(@PathVariable("attrId") Long attrId) {
    attrService.removeById(attrId);

    return R.ok();
  }
}
