package ec.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.ware.model.entity.PurchaseEntity;
import ec.ware.model.vo.PurchaseMergeVO;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
public interface PurchaseService extends IService<PurchaseEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * Query purchase list, which status is unreceived.
   *
   * @param params
   * @return
   */
  PageUtils queryUnReceivedPage(Map<String, Object> params);

  /**
   * Merge purchase requirement to purchase list.<br>
   * If {@link PurchaseMergeVO#getPurchaseId()} is null, it will create new purchase list.<br>
   *
   * @param vo
   */
  void mergePurchase(PurchaseMergeVO vo);

  /**
   * Employee can receive specified purchase.
   *
   * @param ids
   */
  void receive(List<Long> ids);
}
