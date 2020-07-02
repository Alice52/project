## seckill-project

### core

1. 有限的服务器资源, 尽可能多地处理短时间内的海量请求

### backend

1. 缓存: redis + jedis

   - 预热
   - 分布式 token
   - 超卖: 预减库存
   - 流控: 令牌桶

2. MQ 异步下单: mvc async
3. 图形验证码
4. 接口限流:

   - `@AccessLimit`
   - 接口隐藏

5. 超卖: `SQL`
6. 预减库存

   - memery
   - redis

### DB Design

1. user
2. goods
3. seckillGoods: 通过 商品的 ID 关联到商品表

   - 这里为了活动的灵活性
   - 减少对原系统的干扰, 秒杀结束就结束了, 数据代码归档

4. order
5. seckillOrder
