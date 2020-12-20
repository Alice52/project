package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.HomeSubjectEntity;
import ec.coupon.service.HomeSubjectService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/homesubject")
public class HomeSubjectController extends BaseController {
  @Resource private HomeSubjectService homeSubjectService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = homeSubjectService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  public HomeSubjectEntity info(@PathVariable("id") Long id) {
    HomeSubjectEntity homeSubject = homeSubjectService.getById(id);

    return homeSubject;
  }

  @PostMapping("/save")
  public void save(@RequestBody HomeSubjectEntity homeSubject) {
    homeSubjectService.save(homeSubject);
  }

  @PutMapping("/update/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody HomeSubjectEntity homeSubject) {
    homeSubject.setId(id);
    homeSubjectService.updateById(homeSubject);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    homeSubjectService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    homeSubjectService.removeById(id);
  }
}
