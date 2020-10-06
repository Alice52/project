package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.HomeAdvEntity;
import ec.coupon.service.HomeAdvService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/homeadv")
public class HomeAdvController extends BaseController {
  @Resource private HomeAdvService homeAdvService;

  @GetMapping("/list")
  // @RequiresPermissions("coupon:homeadv:list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = homeAdvService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("coupon:homeadv:info")
  public HomeAdvEntity info(@PathVariable("id") Long id) {
    HomeAdvEntity homeAdv = homeAdvService.getById(id);

    return homeAdv;
  }

  @PostMapping("/save")
  // @RequiresPermissions("coupon:homeadv:save")
  public void save(@RequestBody HomeAdvEntity homeAdv) {
    homeAdvService.save(homeAdv);
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("coupon:homeadv:update")
  public void update(@PathVariable("id") Long id, @RequestBody HomeAdvEntity homeAdv) {
    homeAdv.setId(id);
    homeAdvService.updateById(homeAdv);
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("coupon:homeadv:delete")
  public void delete(@RequestBody Long[] ids) {
    homeAdvService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("coupon:homeadv:delete")
  public void deleteById(@PathVariable("id") Long id) {
    homeAdvService.removeById(id);
  }
}
