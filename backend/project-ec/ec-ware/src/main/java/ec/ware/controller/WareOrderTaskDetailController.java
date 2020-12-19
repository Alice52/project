package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.model.entity.WareOrderTaskDetailEntity;
import ec.ware.model.vo.WareOrderTaskDetailVO;
import ec.ware.service.WareOrderTaskDetailService;
import ec.common.utils.PageUtils;

import static ec.ware.converter.WareOrderTaskDetailConverter.INSTANCE;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@RestController
@RequestMapping("/ware")
public class WareOrderTaskDetailController {
  @Resource private WareOrderTaskDetailService wareOrderTaskDetailService;

  @GetMapping("/wareordertaskdetails")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return wareOrderTaskDetailService.queryPage(params);
  }

  @GetMapping("/wareordertaskdetail/{id}")
  public WareOrderTaskDetailVO detail(@PathVariable("id") Long id) {
    WareOrderTaskDetailEntity po = wareOrderTaskDetailService.getById(id);

    return INSTANCE.po2vo(po);
  }

  @PostMapping("/wareordertaskdetail")
  public void save(@RequestBody WareOrderTaskDetailVO wareOrderTaskDetailVO) {

    wareOrderTaskDetailService.save(INSTANCE.vo2po(wareOrderTaskDetailVO));
  }

  @PutMapping("/wareordertaskdetail/{id}")
  public void update(
      @PathVariable("id") Long id, @RequestBody WareOrderTaskDetailVO wareOrderTaskDetailVO) {

    wareOrderTaskDetailVO.setId(id);
    wareOrderTaskDetailService.updateById(INSTANCE.vo2po(wareOrderTaskDetailVO));
  }

  @DeleteMapping("/wareordertaskdetails")
  public void delete(@RequestBody Long[] ids) {

    wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/wareordertaskdetail/{id}")
  public void deleteById(@PathVariable("id") Long id) {

    wareOrderTaskDetailService.removeById(id);
  }
}
