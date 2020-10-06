package ec.order.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import ec.order.entity.PaymentInfoEntity;
import ec.order.service.PaymentInfoService;
import ec.common.utils.PageUtils;
import ec.common.utils.R;

/**
 * 支付信息表
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:40:52
 */
@Api
@RestController
@RequestMapping("order/paymentinfo")
public class PaymentInfoController {
  @Resource private PaymentInfoService paymentInfoService;

  @GetMapping("/list")
  // @RequiresPermissions("order:paymentinfo:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = paymentInfoService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("order:paymentinfo:info")
  public R info(@PathVariable("id") Long id) {
    PaymentInfoEntity paymentInfo = paymentInfoService.getById(id);

    return R.ok().put("paymentInfo", paymentInfo);
  }

  @PostMapping("/save")
  // @RequiresPermissions("order:paymentinfo:save")
  public R save(@RequestBody PaymentInfoEntity paymentInfo) {
    paymentInfoService.save(paymentInfo);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("order:paymentinfo:update")
  public R update(@PathVariable("id") Long id, @RequestBody PaymentInfoEntity paymentInfo) {
    paymentInfo.setId(id);
    paymentInfoService.updateById(paymentInfo);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("order:paymentinfo:delete")
  public R delete(@RequestBody Long[] ids) {
    paymentInfoService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("order:paymentinfo:delete")
  public R deleteById(@PathVariable("id") Long id) {
    paymentInfoService.removeById(id);

    return R.ok();
  }
}
