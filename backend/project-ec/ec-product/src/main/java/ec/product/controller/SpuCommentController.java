package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SpuCommentEntity;
import ec.product.service.SpuCommentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品评价
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("product/spucomment")
public class SpuCommentController {
  @Resource private SpuCommentService spuCommentService;

  @GetMapping("/list")
  // @RequiresPermissions("product:spucomment:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuCommentService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:spucomment:info")
  public R info(@PathVariable("id") Long id) {
    SpuCommentEntity spuComment = spuCommentService.getById(id);

    return R.ok().put("spuComment", spuComment);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:spucomment:save")
  public R save(@RequestBody SpuCommentEntity spuComment) {
    spuCommentService.save(spuComment);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:spucomment:update")
  public R update(@PathVariable("id") Long id, @RequestBody SpuCommentEntity spuComment) {
    spuComment.setId(id);
    spuCommentService.updateById(spuComment);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:spucomment:delete")
  public R delete(@RequestBody Long[] ids) {
    spuCommentService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:spucomment:delete")
  public R deleteById(@PathVariable("id") Long id) {
    spuCommentService.removeById(id);

    return R.ok();
  }
}
