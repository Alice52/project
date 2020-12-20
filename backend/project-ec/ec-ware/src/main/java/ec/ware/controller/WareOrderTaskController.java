package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.model.entity.WareOrderTaskEntity;
import ec.ware.model.vo.WareOrderTaskVO;
import ec.ware.service.WareOrderTaskService;
import ec.common.utils.PageUtils;

import static ec.ware.converter.WareOrderTaskConverter.INSTANCE;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@RestController
@RequestMapping("/ware")
public class WareOrderTaskController {
  @Resource private WareOrderTaskService wareOrderTaskService;

  @GetMapping("/ware-order-tasks")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return wareOrderTaskService.queryPage(params);
  }

  @GetMapping("/ware-order-task/{id}")
  public WareOrderTaskVO detail(@PathVariable("id") Long id) {
    WareOrderTaskEntity po = wareOrderTaskService.getById(id);

    return INSTANCE.po2vo(po);
  }

  @PostMapping(value = {"/ware-order-task", "/ware-order-tasks"})
  public void save(@RequestBody WareOrderTaskVO wareOrderTaskVO) {

    wareOrderTaskService.save(INSTANCE.vo2po(wareOrderTaskVO));
  }

  @PutMapping("/ware-order-task/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody WareOrderTaskVO wareOrderTaskVO) {

    wareOrderTaskVO.setId(id);
    wareOrderTaskService.updateById(INSTANCE.vo2po(wareOrderTaskVO));
  }

  @DeleteMapping("/ware-order-tasks")
  public void delete(@RequestBody Long[] ids) {

    wareOrderTaskService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/ware-order-task/{id}")
  public void deleteById(@PathVariable("id") Long id) {

    wareOrderTaskService.removeById(id);
  }
}
