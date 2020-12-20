package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.HomeSubjectSpuEntity;
import ec.coupon.service.HomeSubjectSpuService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 专题商品
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/homesubjectspu")
public class HomeSubjectSpuController extends BaseController {
  @Resource private HomeSubjectSpuService homeSubjectSpuService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = homeSubjectSpuService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  public HomeSubjectSpuEntity info(@PathVariable("id") Long id) {
    HomeSubjectSpuEntity homeSubjectSpu = homeSubjectSpuService.getById(id);

    return homeSubjectSpu;
  }

  @PostMapping("/save")
  public void save(@RequestBody HomeSubjectSpuEntity homeSubjectSpu) {
    homeSubjectSpuService.save(homeSubjectSpu);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody HomeSubjectSpuEntity homeSubjectSpu) {
    homeSubjectSpu.setId(id);
    homeSubjectSpuService.updateById(homeSubjectSpu);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    homeSubjectSpuService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    homeSubjectSpuService.removeById(id);
  }
}
