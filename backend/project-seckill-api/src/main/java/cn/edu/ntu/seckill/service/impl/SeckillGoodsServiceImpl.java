package cn.edu.ntu.seckill.service.impl;

import cn.edu.ntu.seckill.component.CacheUtils;
import cn.edu.ntu.seckill.converter.SeckillGoodsConverter;
import cn.edu.ntu.seckill.exception.GoodsException;
import cn.edu.ntu.seckill.exception.SeckillGoodsException;
import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.bo.SeckillGoodsBO;
import cn.edu.ntu.seckill.model.po.SeckillGoodsPO;
import cn.edu.ntu.seckill.model.po.SeckillStockPO;
import cn.edu.ntu.seckill.model.vo.ListVO;
import cn.edu.ntu.seckill.model.vo.SeckillGoodsVO;
import cn.edu.ntu.seckill.redis.RedisGoodsKeyEnum;
import cn.edu.ntu.seckill.redis.RedisSeckillGoodsKeyEnum;
import cn.edu.ntu.seckill.repository.IGoodsRepository;
import cn.edu.ntu.seckill.repository.ISeckillGoodsRepository;
import cn.edu.ntu.seckill.repository.ISeckillStockRepository;
import cn.edu.ntu.seckill.service.ISeckillGoodsService;
import cn.edu.ntu.seckill.utils.RedisKeyUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-17 21:28 <br>
 * @project project-seckill <br>
 */
@Service
public class SeckillGoodsServiceImpl implements ISeckillGoodsService {

  @Resource private ISeckillGoodsRepository seckillGoodsRepository;
  @Resource private ISeckillStockRepository seckillStockRepository;
  @Resource private IGoodsRepository goodsRepository;
  @Resource private CacheUtils<SeckillGoodsBO> cacheService;
  @Resource private CacheUtils<GoodsBO> goodsCacheService;
  @Resource private RedisTemplate redisTemplate;

  @Override
  public SeckillGoodsVO view(@NotBlank String goodsId) {
    SeckillGoodsBO condition = new SeckillGoodsBO();
    condition.setGoodsId(goodsId);
    SeckillGoodsBO goods = validateAndGetByConditionThenCache(goodsId, condition);

    SeckillStockPO stockPO = seckillStockRepository.getBySeckillGoodsId(goods.getId());
    goods.setStock(ObjectUtil.isNull(stockPO) ? 0 : stockPO.getStock());

    return SeckillGoodsConverter.INSTANCE.bo2vo(goods);
  }

  @Override
  public ListVO<SeckillGoodsVO> list(
      @NotNull Integer pageSize, @NotNull Integer currentPage, String searchKey) {
    return null;
  }

  @Override
  public String updateStock(@NotBlank String seckillGoodsId, @Min(0) @NotNull Integer stock) {
    SeckillGoodsBO condition = new SeckillGoodsBO();
    condition.setId(seckillGoodsId);
    SeckillGoodsBO goodsBO = validateAndGetByConditionThenCache(seckillGoodsId, condition);

    int updateStockCount = seckillStockRepository.updateStock(seckillGoodsId, stock);
    if (updateStockCount <= 0) {
      throw new SeckillGoodsException().new SeckillGoodsStockException(stock);
    }

    // update stock count in redis cache
    initCacheInRedis(goodsBO.getId(), stock);

    return goodsBO.getId();
  }

  private SeckillGoodsBO validateAndGetByConditionThenCache(String key, SeckillGoodsBO condition) {

    SeckillGoodsBO goods = getSeckillGoodsVOFromCache(key);
    if (ObjectUtil.isNotNull(goods)) {
      return goods;
    }

    SeckillGoodsPO po = seckillGoodsRepository.getByCondition(condition);
    validateExistence(po, key);
    goods = SeckillGoodsConverter.INSTANCE.po2bo(po);

    cacheSeckillGoods(goods, key);
    return goods;
  }

  /**
   * Validate object existence, obtained by maker.
   *
   * @param object
   * @param marker
   */
  private void validateExistence(Object object, Object marker) {
    if (ObjectUtil.isNull(object)) {
      throw new SeckillGoodsException().new SeckillGoodsNotExistenceException(marker);
    }
  }

  private SeckillGoodsBO getSeckillGoodsVOFromCache(String key) {

    if (StrUtil.isBlank(key)) {
      return null;
    }

    return cacheService.get(RedisKeyUtils.buildKey(RedisSeckillGoodsKeyEnum.SECKILL_GOODS, key));
  }

  @Override
  public String publishPromo(SeckillGoodsBO seckillGoodsBO) {

    validateDuplicateGoods(seckillGoodsBO.getGoodsId());
    SeckillGoodsPO po = SeckillGoodsConverter.INSTANCE.bo2po(seckillGoodsBO);
    seckillGoodsRepository.create(po);

    SeckillStockPO stockPO = new SeckillStockPO(po.getId(), seckillGoodsBO.getStock());
    seckillStockRepository.create(stockPO);

    // if update this goods, please delete the redis cache.
    cacheSeckillGoods(SeckillGoodsConverter.INSTANCE.po2bo(po));

    initCacheInRedis(po.getId(), seckillGoodsBO.getStock());
    return po.getId();
  }

  private void initCacheInRedis(String seckillGoodsId, Integer stock) {
    // set stock in redis cache
    redisTemplate
        .opsForValue()
        .set(
            RedisKeyUtils.buildKey(RedisSeckillGoodsKeyEnum.SECKILL_GOODS_STOCK, seckillGoodsId),
            stock);

    // set request threshold
    redisTemplate
        .opsForValue()
        .set(
            RedisKeyUtils.buildKey(
                RedisSeckillGoodsKeyEnum.SECKILL_GOODS_THRESHOLDS, seckillGoodsId),
            stock * 5);
  }

  private void validateDuplicateGoods(String goodsId) {
    validateDuplicateGoods(goodsId, null);
  }

  /**
   * Validate goods info duplicates.
   *
   * @param goodsId
   * @param updatedGoodId
   */
  private void validateDuplicateGoods(String goodsId, String updatedGoodId) {

    // validate goods id in goods.
    GoodsBO bo = goodsCacheService.get(RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, goodsId));
    if (ObjectUtil.isNull(bo)) {
      GoodsBO goodsBO = new GoodsBO();
      goodsBO.setId(goodsId);
      if (ObjectUtil.isNull(goodsRepository.getByCondition(goodsBO))) {
        throw new GoodsException().new GoodsNotExistenceException(goodsId);
      }
    }

    // get from cache
    SeckillGoodsBO goods =
        cacheService.get(RedisKeyUtils.buildKey(RedisSeckillGoodsKeyEnum.SECKILL_GOODS, goodsId));

    if (ObjectUtil.isNull(goods)) {
      SeckillGoodsPO po = seckillGoodsRepository.getByPK(goodsId);
      if (ObjectUtil.isNull(po)) {
        return;
      }

      goods = SeckillGoodsConverter.INSTANCE.po2bo(po);
      cacheSeckillGoods(goods, goodsId);
    }

    boolean updatedDuplicate =
        StrUtil.isNotBlank(updatedGoodId) && !StrUtil.equals(goods.getId(), updatedGoodId);
    if (StrUtil.isBlank(updatedGoodId) || updatedDuplicate) {
      throw new SeckillGoodsException().new SeckillGoodsDuplicateException(goodsId);
    }
  }

  /**
   * Cache goods to redis, and can retire by goodsId.
   *
   * @param bo
   */
  private void cacheSeckillGoods(SeckillGoodsBO bo, String key, long seconds) {
    String realKey =
        RedisKeyUtils.buildKey(
            RedisSeckillGoodsKeyEnum.SECKILL_GOODS, key == null ? bo.getId() : key);
    cacheService.cache(bo, realKey, seconds);
  }

  private void cacheSeckillGoods(SeckillGoodsBO bo, String key) {
    cacheSeckillGoods(bo, key, 2 * 60);
  }

  private void cacheSeckillGoods(SeckillGoodsBO bo) {
    cacheSeckillGoods(bo, null);
  }
}
