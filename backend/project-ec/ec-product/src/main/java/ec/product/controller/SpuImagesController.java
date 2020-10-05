package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SpuImagesEntity;
import ec.product.service.SpuImagesService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * spu图片
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("product/spuimages")
public class SpuImagesController {
  @Resource private SpuImagesService spuImagesService;

  @GetMapping("/list")
  // @RequiresPermissions("product:spuimages:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuImagesService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:spuimages:info")
  public R info(@PathVariable("id") Long id) {
    SpuImagesEntity spuImages = spuImagesService.getById(id);

    return R.ok().put("spuImages", spuImages);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:spuimages:save")
  public R save(@RequestBody SpuImagesEntity spuImages) {
    spuImagesService.save(spuImages);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:spuimages:update")
  public R update(@PathVariable("id") Long id, @RequestBody SpuImagesEntity spuImages) {
    spuImages.setId(id);
    spuImagesService.updateById(spuImages);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:spuimages:delete")
  public R delete(@RequestBody Long[] ids) {
    spuImagesService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:spuimages:delete")
  public R deleteById(@PathVariable("id") Long id) {
    spuImagesService.removeById(id);

    return R.ok();
  }
}
