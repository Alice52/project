package ec.ware.controller;

import ec.common.utils.PageUtils;
import ec.ware.model.entity.PurchaseEntity;
import ec.ware.model.vo.PurchaseMergeVO;
import ec.ware.model.vo.PurchaseVO;
import ec.ware.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ec.ware.converter.PurchaseConverter.INSTANCE;

/**
 * 采购信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@RestController
@RequestMapping("/ware")
public class PurchaseController {
  @Resource private PurchaseService purchaseService;

  @PutMapping("/purchase/receive")
  public void receive(@RequestBody List<Long> ids) {

    purchaseService.receive(ids);
  }

  @PutMapping("/purchase/merge")
  public void merge(@RequestBody PurchaseMergeVO vo) {

    purchaseService.mergePurchase(vo);
  }

  @GetMapping("/purchase/unreceives")
  public PageUtils unreceives(@RequestParam Map<String, Object> params) {

    return purchaseService.queryUnReceivedPage(params);
  }

  @GetMapping("/purchases")
  public PageUtils list(@RequestParam Map<String, Object> params) {

    return purchaseService.queryPage(params);
  }

  @GetMapping("/purchase/{id}")
  public PurchaseVO detail(@PathVariable("id") Long id) {
    PurchaseEntity po = purchaseService.getById(id);

    return INSTANCE.po2vo(po);
  }

  @PostMapping(value = {"/purchase", "/purchases"})
  public void save(@RequestBody PurchaseVO purchaseVO) {

    purchaseVO.setUpdateTime(LocalDateTime.now());
    purchaseVO.setCreateTime(LocalDateTime.now());
    purchaseService.save(INSTANCE.vo2po(purchaseVO));
  }

  @PutMapping("/purchase/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody PurchaseVO purchaseVO) {

    purchaseVO.setId(id);
    purchaseService.updateById(INSTANCE.vo2po(purchaseVO));
  }

  @DeleteMapping("/purchases")
  public void delete(@RequestBody Long[] ids) {

    purchaseService.removeByIds(Arrays.asList(ids));
  }

  @DeleteMapping("/purchase/{id}")
  public void deleteById(@PathVariable("id") Long id) {

    purchaseService.removeById(id);
  }
}
