package cn.edu.ntu.seckill.service.impl;

import cn.edu.ntu.seckill.component.CacheUtils;
import cn.edu.ntu.seckill.converter.GoodsConverter;
import cn.edu.ntu.seckill.exception.BusinessException;
import cn.edu.ntu.seckill.exception.GoodsException;
import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.po.GoodsPO;
import cn.edu.ntu.seckill.model.vo.GoodsVO;
import cn.edu.ntu.seckill.model.vo.ListVO;
import cn.edu.ntu.seckill.model.vo.Pagination;
import cn.edu.ntu.seckill.mq.MqProducer;
import cn.edu.ntu.seckill.redis.RedisGoodsKeyEnum;
import cn.edu.ntu.seckill.repository.IGoodsRepository;
import cn.edu.ntu.seckill.service.IGoodsService;
import cn.edu.ntu.seckill.utils.PaginationUtils;
import cn.edu.ntu.seckill.utils.RedisKeyUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zack <br>
 * @create 2020-08-11 22:19 <br>
 * @project project-seckill <br>
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

  @Resource private IGoodsRepository goodsRepository;
  @Resource private RedisTemplate redisTemplate;
  @Resource private CacheUtils<GoodsBO> cacheService;

  @Override
  public String create(GoodsBO goods) {

    validateDuplicateName(goods.getName());

    GoodsPO po = GoodsConverter.INSTANCE.bo2po(goods);
    goodsRepository.create(po);

    // if update this goods, please delete the redis cache.
    cacheGoods(GoodsConverter.INSTANCE.po2bo(po));

    return po.getId();
  }

  @Override
  public GoodsVO getById(String goodsId) {

    GoodsBO condition = new GoodsBO();
    condition.setId(goodsId);
    GoodsBO goods = validateAndGetByConditionThenCache(goodsId, condition);

    return GoodsConverter.INSTANCE.bo2vo(goods);
  }

  @Override
  public GoodsVO getByName(@NotBlank String name) {
    GoodsBO condition = new GoodsBO();
    condition.setName(name);

    GoodsBO goods = validateAndGetByConditionThenCache(name, condition);

    return GoodsConverter.INSTANCE.bo2vo(goods);
  }

  @Override
  public String fullScaleUpdate(GoodsBO goods, boolean isFullScaleUpdate) {

    validateDuplicateName(goods.getName(), goods.getId());

    GoodsPO goodsPO = GoodsConverter.INSTANCE.bo2po(goods);
    if (isFullScaleUpdate) {
      goodsRepository.fullScaleUpdate(goods.getId(), goodsPO);
    } else {
      goodsRepository.update(goods.getId(), goodsPO);
    }

    // remove the redis cache of this goods
    redisTemplate.delete(RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, goods.getId()));
    GoodsBO newBO = GoodsConverter.INSTANCE.po2bo(goodsPO);
    cacheGoods(newBO);

    return goods.getId();
  }

  @Override
  public ListVO<GoodsVO> list(Integer pageSize, Integer currentPage, String searchKey) {

    Integer total = goodsRepository.count(searchKey);
    Pagination pagination = PaginationUtils.buildPagination(total, pageSize, currentPage);
    Integer offSet = (pagination.getCurrentPage() - 1) * pagination.getPageSize();
    List<GoodsPO> pos = goodsRepository.list(pageSize, offSet, searchKey);

    return new ListVO(pagination, GoodsConverter.INSTANCE.pos2vos(pos));
  }

  /**
   * Get GoodsBO from cache first, <br>
   * if not exist, it will return data queried from database, <br>
   * else will return null
   *
   * @param key
   * @param condition
   * @return
   */
  @Override
  public GoodsBO validateAndGetByConditionThenCache(String key, GoodsBO condition) {
    GoodsBO goods = getGoodsVOFromCache(key);

    if (ObjectUtil.isNotNull(goods)) {
      return goods;
    }

    GoodsPO po = goodsRepository.getByCondition(condition);
    validateExistence(po, key);
    goods = GoodsConverter.INSTANCE.po2bo(po);

    cacheGoods(goods, key);
    return goods;
  }

  @Override
  public boolean decreaseStock(@NotBlank String goodsId, @Min(1) Integer amount)
      throws BusinessException {

    int count = goodsRepository.decreaseStock(goodsId, amount);
    if (count > 0) {
      return true;
    }

    return false;
  }

  /**
   * Validate object existence, obtained by maker.
   *
   * @param object
   * @param marker
   */
  private void validateExistence(Object object, Object marker) {
    if (ObjectUtil.isNull(object)) {
      throw new GoodsException().new GoodsNotExistenceException(marker);
    }
  }

  /**
   * Validate PO existence:
   *
   * <p>If not existence, throw GoodsNotExistenceException, <br>
   * else convert to BO.
   *
   * @param po
   * @param maker
   * @return
   */
  private GoodsBO ensureExistenceThenConvert(GoodsPO po, GoodsBO maker) {
    validateExistence(po, maker);

    return GoodsConverter.INSTANCE.po2bo(po);
  }

  /**
   * Validate goods name duplicated.
   *
   * @param goodsName
   */
  private void validateDuplicateName(String goodsName) {
    validateDuplicateName(goodsName, null);
  }

  /**
   * Validate the name of the goods whether it is duplicated.<br>
   * If it is duplicated, will throw an GoodsNameDuplicateException.
   *
   * @param goodsName
   * @param updatedGoodId
   */
  private void validateDuplicateName(String goodsName, String updatedGoodId) {

    // get from cache
    GoodsBO goods = cacheService.get(RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, goodsName));

    if (ObjectUtil.isNull(goods)) {
      GoodsPO po = goodsRepository.queryByName(goodsName);
      if (ObjectUtil.isNull(po)) {
        return;
      }

      goods = GoodsConverter.INSTANCE.po2bo(po);
      cacheGoods(goods, goodsName);
    }

    boolean updatedDuplicate =
        StrUtil.isNotBlank(updatedGoodId) && !StrUtil.equals(goods.getId(), updatedGoodId);
    if (StrUtil.isBlank(updatedGoodId) || updatedDuplicate) {
      throw new GoodsException().new GoodsNameDuplicateException(goodsName);
    }
  }

  /**
   * Cache goods to redis, and can retire by goodsId.
   *
   * @param bo
   */
  private void cacheGoods(GoodsBO bo, String key, long seconds) {
    String realKey =
        RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, key == null ? bo.getId() : key);
    cacheService.set(bo, realKey, seconds, TimeUnit.SECONDS);
  }

  private void cacheGoods(GoodsBO bo, String key) {
    cacheGoods(bo, key, 2 * 60);
  }

  private void cacheGoods(GoodsBO bo) {
    cacheGoods(bo, null);
  }

  /**
   * Get GoodsBO from Redis cache.
   *
   * @param key
   * @return
   */
  private GoodsBO getGoodsVOFromCache(String key) {

    if (StrUtil.isBlank(key)) {
      return null;
    }

    return cacheService.get(RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, key));
  }
}
