package ec.ware.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ec.common.constants.WareConstants;
import ec.common.utils.CommonQuery;
import ec.common.utils.PageUtils;
import ec.ware.model.entity.PurchaseDetailEntity;
import ec.ware.model.entity.PurchaseEntity;
import ec.ware.model.vo.PurchaseMergeVO;
import ec.ware.repository.PurchaseRepository;
import ec.ware.service.PurchaseDetailService;
import ec.ware.service.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 采购信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseRepository, PurchaseEntity>
    implements PurchaseService {

  @Resource PurchaseDetailService purchaseDetailService;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    QueryWrapper<PurchaseEntity> wrapper = new QueryWrapper<>();
    Object status = params.get("status");
    if (ObjectUtil.isNotNull(status) && StrUtil.isNotBlank(status.toString())) {
      wrapper.eq("status", status);
    }

    Object key = params.get("key");
    if (ObjectUtil.isNotNull(key) && StrUtil.isNotBlank(key.toString())) {
      wrapper.and(
          w ->
              w.like("assignee_name", key)
                  .or()
                  .like("assignee_id", key)
                  .or()
                  .eq("phone", key)
                  .or()
                  .like("ware_id", key));
    }

    IPage<PurchaseEntity> page =
        this.page(new CommonQuery<PurchaseEntity>().getPage(params), wrapper);

    return new PageUtils(page);
  }

  @Override
  public PageUtils queryUnReceivedPage(Map<String, Object> params) {
    IPage<PurchaseEntity> page =
        this.page(
            new CommonQuery<PurchaseEntity>().getPage(params),
            new QueryWrapper<PurchaseEntity>().eq("status", 0).or().eq("status", 1));

    return new PageUtils(page);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void mergePurchase(PurchaseMergeVO vo) {

    if (ObjectUtil.isNull(vo.getPurchaseId())) {
      PurchaseEntity entity = new PurchaseEntity();
      entity.setStatus(WareConstants.PurchaseStatusEnum.CREATED.getCode());
      entity.setCreateTime(LocalDateTime.now());
      entity.setUpdateTime(LocalDateTime.now());
      this.save(entity);
      vo.setPurchaseId(entity.getId());
    }

    List<Long> items = vo.getItems();

    List<PurchaseDetailEntity> entities =
        items.stream()
            .map(
                x -> {
                  PurchaseDetailEntity entity = new PurchaseDetailEntity();
                  entity.setPurchaseId(vo.getPurchaseId());
                  entity.setId(x);
                  entity.setStatus(WareConstants.PurchaseDetailStatusEnum.ASSIGNED.getCode());
                  return entity;
                })
            .collect(Collectors.toList());

    purchaseDetailService.updateBatchById(entities);

    UpdateWrapper<PurchaseEntity> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", vo.getPurchaseId()).set("update_time", LocalDateTime.now());
    this.update(updateWrapper);
  }

  @Override
  public void receive(List<Long> ids) {

    List<PurchaseEntity> entities =
        this.listByIds(ids).stream()
            .filter(
                x ->
                    x.getStatus() == WareConstants.PurchaseStatusEnum.CREATED.getCode()
                        || x.getStatus() == WareConstants.PurchaseStatusEnum.ASSIGNED.getCode())
            .map(
                y -> {
                  y.setStatus(WareConstants.PurchaseStatusEnum.RECEIVED.getCode());
                  y.setUpdateTime(LocalDateTime.now());
                  return y;
                })
            .collect(Collectors.toList());

    this.updateBatchById(entities);

    entities.forEach(
        x -> {
          List<PurchaseDetailEntity> detailEntities =
              purchaseDetailService.listByPurchaseId(x.getId());

          detailEntities.forEach(
              d -> d.setStatus(WareConstants.PurchaseDetailStatusEnum.BUYING.getCode()));
          purchaseDetailService.updateBatchById(detailEntities);
        });
  }
}
