## project-seckill

![avatar](/docs/static/seckill-plus.png)

### technique

1. MySQL
2. Redis
3. Rocket MQ
4. spring boot
5. spring session
   - only `/user/opt` use spring session, it's session will be whole.
6. open resty
7. cdn
8. mybatis

### backend

1. 分布式

   - nginx 反向代理
   - 分布式会话管理

   ![avatar](/docs/static/user-login-session.png)

2. 缓存: `将数据推到离用户最近的地方`

   - redis 缓存
   - 本地缓存
   - ~~热点 nginx LUA 缓存~~

3. ~~页面静态化~~

   - CDN
   - 动态请求缓存
   - 页面静态化

4. 下单

   - 缓存库存: `缓存击穿`
   - 下单异步化: 两种方式 `异步创建订单 / 异步减库存[行锁]`
   - 异步事务: ROCKET-MQ

5. 流量

   - 秒杀大闸: 生成秒杀令牌的参数
   - 秒杀令牌: 秒杀接口需要的参数
   - 推列泄洪: 维持一个拥塞窗口

6. 防刷限流

   - 验证码
   - 限流器: 令牌桶, 漏桶[以固定的速度流入]

     - **对有锁竞争的操作单线程队列可能效果更好**

     ![avatar](/docs/static/limit.png)

7. ~~防黄牛~~: 设备指纹

8. 性能测试

   - jemter 压测
   - 优化

### frontend

### DB Design

1. 可以把 stock 从 goods 表中单独拆分出来

   - 对这个字段进行读取, innodb 的行锁会阻塞其他线程对整条纪录的读取[有些线程读取的仅仅是商品本身的信息]: 非常消耗性能
   - 可以拆分成单独的表, 就解决了这个问题, 而拆成小表之后且 `通过异步减库存的方式[间接的行锁后置]` 读取速度会相应的变快

2. 可以把 password 从 user 表中单独拆分出来

   - same as 1.

3. 用户注册时需要校验 email 重复的问题:
   - 现在是先查询了一遍数据库, 做校验
   - 之后再进行插入或者异常
   - 优化: 考虑在 email 和 isDeleted 加复合的 unique 索引, 直接插入注册信息, 处理 duplicated 异常, 对用户进行 duplicated 提示

---

## TODO:

1. execute follow command to change `project-seckill`

   ```shell
   # zsh will error, please chenge to bash
   cd console
   ./change-content-name.sh
   ./rename-project.sh
   ```

2. change package name: `projectname`
3. change `$module` for log

### performance

1. tomcaat
2. redis
