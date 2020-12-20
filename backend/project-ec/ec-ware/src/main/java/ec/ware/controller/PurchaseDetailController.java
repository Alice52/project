package ec.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.ware.model.entity.PurchaseDetailEntity;
import ec.ware.model.vo.PurchaseDetailVO;
import ec.ware.service.PurchaseDetailService;
import ec.common.utils.PageUtils;

import static ec.ware.converter.PurchaseDetailConverter.INSTANCE;

/**
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@RestController
@RequestMapping("/ware")
public class PurchaseDetailController {
  @Resource private PurchaseDetailService purchaseDetailService;

  @GetMapping("/purchase-details")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return purchaseDetailService.queryPage(params);
  }

  @GetMapping("/purchase-detail/{id}")
  public PurchaseDetailVO detail(@PathVariable("id") Long id) {
    PurchaseDetailEntity po = purchaseDetailService.getById(id);

    return INSTANCE.po2vo(po);
  }

  @PostMapping(value = {"/purchase-detail", "/purchase-details"})
  public void save(@RequestBody PurchaseDetailVO purchaseDetailVO) {

    purchaseDetailService.save(INSTANCE.vo2po(purchaseDetailVO));
  }

  @PutMapping("/purchase-detail/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody PurchaseDetailVO purchaseDetailVO) {

    purchaseDetailVO.setId(id);
    purchaseDetailService.updateById(INSTANCE.vo2po(purchaseDetailVO));
  }

  @DeleteMapping("/purchase-details")
  public void delete(@RequestBody Long[] ids) {

    purchaseDetailService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/purchase-detail/{id}")
  public void deleteById(@PathVariable("id") Long id) {

    purchaseDetailService.removeById(id);
  }
}
