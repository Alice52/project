package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.SpuInfoEntity;
import ec.product.model.vo.SpuSaveVO;
import ec.product.service.SpuInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;

/**
 * spu信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:26
 */
@Api
@RestController
@RequestMapping("/product")
public class SpuInfoController {
  @Resource private SpuInfoService spuInfoService;

  @GetMapping("/spu-info")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = spuInfoService.queryPageByCondition(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/spu-info/{id}")
  public R info(@PathVariable("id") Long id) {
    SpuInfoEntity spuInfo = spuInfoService.getById(id);

    return R.ok().put("data", spuInfo);
  }

  @PostMapping(value = "/spu-info")
  public R save(@RequestBody SpuSaveVO vo) {
    spuInfoService.saveSpuInfo(vo);

    return R.ok();
  }

  @PutMapping("/spu-info/up/{id}")
  public R up(@PathVariable("id") @NotNull Long id) {

    spuInfoService.spuUp(id);

    return R.ok();
  }

  @PutMapping("/spu-info/down/{id}")
  public R down(@PathVariable("id") @NotNull Long id) {
    spuInfoService.spuDown(id);

    return R.ok();
  }

  @PutMapping("/spu-info/{id}")
  public R update(@PathVariable("id") Long id, @RequestBody SpuSaveVO vo) {

    return R.ok();
  }

  @DeleteMapping("/spu-info")
  public R delete(@RequestBody Long[] ids) {
    spuInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/spu-info/{id}")
  public R deleteById(@PathVariable("id") Long id) {
    spuInfoService.removeById(id);

    return R.ok();
  }
}
