package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.model.entity.WareInfoEntity;
import ec.ware.model.vo.WareInfoVO;
import ec.ware.service.WareInfoService;
import ec.common.utils.PageUtils;

import static ec.ware.converter.WareInfoConverter.INSTANCE;

/**
 * 仓库信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@RestController
@RequestMapping("/ware")
public class WareInfoController {
  @Resource private WareInfoService wareInfoService;

  @GetMapping("/ware-infos")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return wareInfoService.queryPage(params);
  }

  @GetMapping("/ware-info/{id}")
  public WareInfoVO detail(@PathVariable("id") Long id) {
    WareInfoEntity po = wareInfoService.getById(id);

    return INSTANCE.po2vo(po);
  }

  @PostMapping(value = {"/ware-info", "/ware-infos"})
  public void save(@RequestBody WareInfoVO wareInfoVO) {

    wareInfoService.save(INSTANCE.vo2po(wareInfoVO));
  }

  @PutMapping("/ware-info/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody WareInfoVO wareInfoVO) {

    wareInfoVO.setId(id);
    wareInfoService.updateById(INSTANCE.vo2po(wareInfoVO));
  }

  @DeleteMapping("/ware-infos")
  public void delete(@RequestBody Long[] ids) {

    wareInfoService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/ware-info/{id}")
  public void deleteById(@PathVariable("id") Long id) {

    wareInfoService.removeById(id);
  }
}
