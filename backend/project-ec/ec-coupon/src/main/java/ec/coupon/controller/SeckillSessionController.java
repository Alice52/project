package ec.coupon.controller;

import ec.common.utils.PageUtils;
import ec.coupon.entity.SeckillSessionEntity;
import ec.coupon.service.SeckillSessionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 11:57:24
 */
@Api
@RestController
@RequestMapping("coupon/seckillsession")
public class SeckillSessionController extends BaseController {
  @Resource private SeckillSessionService seckillSessionService;

  @GetMapping("/list")
  public PageUtils list(@RequestParam Map<String, Object> params) {
    PageUtils page = seckillSessionService.queryPage(params);

    return page;
  }

  @GetMapping("/info/{id}")
  public SeckillSessionEntity info(@PathVariable("id") Long id) {
    SeckillSessionEntity seckillSession = seckillSessionService.getById(id);

    return seckillSession;
  }

  @PostMapping("/save")
  public void save(@RequestBody SeckillSessionEntity seckillSession) {
    seckillSessionService.save(seckillSession);
  }

  @PutMapping("/update/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody SeckillSessionEntity seckillSession) {
    seckillSession.setId(id);
    seckillSessionService.updateById(seckillSession);
  }

  @DeleteMapping("/delete")
  public void delete(@RequestBody Long[] ids) {
    seckillSessionService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    seckillSessionService.removeById(id);
  }
}
