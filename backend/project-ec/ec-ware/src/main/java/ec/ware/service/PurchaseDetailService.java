package ec.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.ware.model.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Get PurchaseDetailEntity by PurchaseId
   *
   * @param purchaseId
   * @return
   */
  List<PurchaseDetailEntity> listByPurchaseId(Long purchaseId);
}
