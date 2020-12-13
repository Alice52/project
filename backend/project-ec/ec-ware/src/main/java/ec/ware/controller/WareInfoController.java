package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.entity.WareInfoEntity;
import ec.ware.service.WareInfoService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 仓库信息
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:43:08
 */
@Api
@RestController
@RequestMapping("ware/wareinfo")
public class WareInfoController {
  @Resource private WareInfoService wareInfoService;

  @GetMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = wareInfoService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    WareInfoEntity wareInfo = wareInfoService.getById(id);

    return R.ok().put("wareInfo", wareInfo);
  }

  @PostMapping("/save")
  public R save(@RequestBody WareInfoEntity wareInfo) {
    wareInfoService.save(wareInfo);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  public R update(@PathVariable("id") Long id, @RequestBody WareInfoEntity wareInfo) {
    wareInfo.setId(id);
    wareInfoService.updateById(wareInfo);

    return R.ok();
  }

  @DeleteMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    wareInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  public R deleteById(@PathVariable("id") Long id) {
    wareInfoService.removeById(id);

    return R.ok();
  }
}
