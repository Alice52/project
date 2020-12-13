package ec.product.feign;

import ec.common.utils.R;
import ec.product.model.to.SkuReductionTO;
import ec.product.model.to.SpuBoundTO;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020/12/13 <br>
 * @project project-ec <br>
 */
@Component
public class CouponFeignServiceHandler implements CouponFeignService {
  @Override
  public R saveSpuBounds(SpuBoundTO spuBoundTO) {
    return R.error("call saveSpuBounds failed");
  }

  @Override
  public R saveSkuReductionInfo(@NotNull SkuReductionTO reductionTO) {
    return R.error("call saveSkuReductionInfo failed");
  }
}
