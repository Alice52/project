package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.model.entity.WareSkuEntity;
import ec.ware.model.vo.WareSkuVO;
import ec.ware.service.WareSkuService;
import ec.common.utils.PageUtils;

import static ec.ware.converter.WareSkuConverter.INSTANCE;

/**
 * 商品库存
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@RestController
@RequestMapping("/ware")
public class WareSkuController {
  @Resource private WareSkuService wareSkuService;

  @GetMapping("/ware-skus")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return wareSkuService.queryPage(params);
  }

  @GetMapping("/ware-sku/{id}")
  public WareSkuVO detail(@PathVariable("id") Long id) {
    WareSkuEntity po = wareSkuService.getById(id);

    return INSTANCE.po2vo(po);
  }

  @PostMapping(value = {"/ware-sku", "/ware-skus"})
  public void save(@RequestBody WareSkuVO wareSkuVO) {

    wareSkuService.save(INSTANCE.vo2po(wareSkuVO));
  }

  @PutMapping("/ware-sku/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody WareSkuVO wareSkuVO) {

    wareSkuVO.setId(id);
    wareSkuService.updateById(INSTANCE.vo2po(wareSkuVO));
  }

  @DeleteMapping("/ware-skus")
  public void delete(@RequestBody Long[] ids) {

    wareSkuService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/ware-sku/{id}")
  public void deleteById(@PathVariable("id") Long id) {

    wareSkuService.removeById(id);
  }
}
