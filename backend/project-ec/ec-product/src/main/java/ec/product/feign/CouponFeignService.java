package ec.product.feign;

import ec.common.utils.R;
import ec.product.model.to.SkuReductionTO;
import ec.product.model.to.SpuBoundTO;
import lombok.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@FeignClient(name = "${service.COUPON-SERVICE}", fallback = CouponFeignServiceHandler.class)
public interface CouponFeignService {

  /**
   * Call api to save spu bounds info.
   *
   * @param spuBoundTO
   * @return R
   */
  @PostMapping("/coupon/spu-bounds")
  R saveSpuBounds(@RequestBody SpuBoundTO spuBoundTO);

  /**
   * Call api to save sku reduction info.
   *
   * @param reductionTO
   * @return R
   */
  @PostMapping("/coupon/sku-reduction")
  R saveSkuReductionInfo(@RequestBody @NotNull SkuReductionTO reductionTO);
}
