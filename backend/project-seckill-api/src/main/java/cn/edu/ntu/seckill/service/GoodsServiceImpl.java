package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.converter.GoodsConverter;
import cn.edu.ntu.seckill.exception.GoodsException;
import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.po.GoodsPO;
import cn.edu.ntu.seckill.model.vo.GoodsVO;
import cn.edu.ntu.seckill.redis.RedisGoodsKeyEnum;
import cn.edu.ntu.seckill.repository.IGoodsRepository;
import cn.edu.ntu.seckill.utils.RedisKeyUtils;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zack <br>
 * @create 2020-08-11 22:19 <br>
 * @project project-seckill <br>
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

  @Resource private IGoodsRepository goodsRepository;
  @Resource private RedisTemplate redisTemplate;

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

    GoodsBO goods = getBOById(goodsId);
    cacheGoods(goods);

    return GoodsConverter.INSTANCE.bo2vo(goods);
  }

  @Override
  public String update(GoodsBO goods) {

    // remove the redis cache of this goods
    redisTemplate.delete(RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, goods.getId()));

    GoodsPO goodsPO = GoodsConverter.INSTANCE.bo2po(goods);
    goodsRepository.update(goods.getId(), goodsPO);
    GoodsBO newBO = GoodsConverter.INSTANCE.po2bo(goodsPO);

    cacheGoods(newBO);

    return newBO.getId();
  }

  /**
   * Get GoodsBO from Redis cache.
   *
   * @param goodsId
   * @return
   */
  private GoodsBO getGoodsVOFromCache(String goodsId) {
    String realKey = RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, goodsId);
    Object object = redisTemplate.opsForValue().get(realKey);

    if (object instanceof GoodsBO) {
      return (GoodsBO) object;
    }

    return null;
  }

  /**
   * Get GoodsBO from cache first, <br>
   * if not exist, it will return data queried from database, <br>
   * else will return null
   *
   * @param goodsId
   * @return
   */
  private GoodsBO getBOById(String goodsId) {
    GoodsBO goods = getGoodsVOFromCache(goodsId);

    if (ObjectUtil.isNotNull(goods)) {
      return goods;
    }

    GoodsPO po = goodsRepository.queryById(goodsId);
    if (ObjectUtil.isNull(po)) {
      throw new GoodsException().new GoodsNotExistenceException(goodsId);
    }

    goods = GoodsConverter.INSTANCE.po2bo(po);

    return goods;
  }

  /**
   * Validate the name of the goods whether it is duplicated.<br>
   * If it is duplicated, will throw an GoodsNameDuplicateException.
   *
   * @param goodsName
   */
  private void validateDuplicateName(String goodsName) {
    GoodsPO po = goodsRepository.queryByName(goodsName);

    Optional.ofNullable(po)
        .ifPresent(
            x -> {
              throw new GoodsException().new GoodsNameDuplicateException(goodsName);
            });

    cacheGoods(GoodsConverter.INSTANCE.po2bo(po));
  }

  /**
   * Cache goods to redis, and can retire by goodsId.
   *
   * @param bo
   */
  private void cacheGoods(GoodsBO bo) {
    Optional.ofNullable(bo.getId())
        .ifPresent(
            x -> {
              redisTemplate
                  .opsForValue()
                  .set(RedisKeyUtils.buildKey(RedisGoodsKeyEnum.GOODS, bo.getId()), bo);
            });
  }
}
