package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.entity.WareSkuEntity;
import ec.ware.service.WareSkuService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 商品库存
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:43:08
 */
@Api
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
  @Resource private WareSkuService wareSkuService;

  @GetMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = wareSkuService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    WareSkuEntity wareSku = wareSkuService.getById(id);

    return R.ok().put("wareSku", wareSku);
  }

  @PostMapping("/save")
  public R save(@RequestBody WareSkuEntity wareSku) {
    wareSkuService.save(wareSku);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  public R update(@PathVariable("id") Long id, @RequestBody WareSkuEntity wareSku) {
    wareSku.setId(id);
    wareSkuService.updateById(wareSku);

    return R.ok();
  }

  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    wareSkuService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  public R deleteById(@PathVariable("id") Long id) {
    wareSkuService.removeById(id);

    return R.ok();
  }
}
