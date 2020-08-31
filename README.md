## project-seckill

### technique

1. MySQL
2. Redis
3. rocket mq
4. spring boot
5. spring sesssion
   - only `/user/opt` use spring session, it's session will be whole.
6. open resty
7. cdn
8. mybatis

### backend

1. 分布式

   - nginx 反向代理
   - 分布式会话管理

   ![avatar](/docs/static//user-login-session.png)

2. 缓存: `将数据推到离用户最近的地方`

   - redis 缓存
   - 本地缓存
   - ~~热点 nginx LUA 缓存~~

3. ~~页面静态化~~

   - cdn
   - 动态请求缓存
   - 页面静态化

4. 下单

   - 缓存库存: `缓存击穿`
   - 下单异步化: 两种方式 `异步创建订单 / 异步减库存[行锁]`
   - 异步事务: rocket-mq

5. 流量

   - 秒杀大闸: 生成秒杀令牌的参数
   - 秒杀令牌: 秒杀接口需要的参数
   - 推列泄洪: 维持一个拥塞窗口

6. 防刷限流

   - 验证码
   - 限流器: 令牌桶, 漏桶[以固定的速度流入]

     - **对有锁竞争的操作单线程队列可能效果更好**

     ![avatar](/docs/static/limit.png)

- ~~防黄牛~~: 设备指纹

7. 性能测试

   - jemter 压测
   - 优化

8. 分布式会话管理

   - 连接: keep-alive 问题
   - token 续期
   - token 安全: ~~放入自定义的 header 中~~/使用自定义协议
   - sso:

     - 同域名

       ![avatar](/docs/static/sso-same-domain.png)

     - 根域名相同子域名不同

       ![avatar](/docs/static/sso-same-top-domain.png)

     - 域名都不同

       ![avatar](/docs/static/sso-diff.png)

9. mysql 优化

   ![avatar](/docs/static/mysql-standalone.png)

   - mysql 应用性能优化: `缓存 + 异步 + 批处理`
     - 写操作: 批量
     - 读操作: 索引
   - 单机配置性能优化
     - max_connection: 1000 default 100
     - innodb_file_per_table: 1
     - innodb_buffer_pool_size: 60-80% 的内存
     - innodb_log_file_size: 256M
     - innodb_log_buffer_size: 16M
     - innodb_flush_log_at_trx_commit:
       - 0: 表示写入 redo/undo log 的 buffer 中就算成功[可能会丢失 1 秒的数据]
       - 1: 表示每次书屋提交都会将数据 flush 到磁盘才算成功[真正的 acid]
       - 2: 表示写入 redo/undo log 的 buffer 中且调用操作系统的 write 方法就算成功[write 方法之后数据被操作系统接管, 会在关机之前写入磁盘]
     - innodb_data_file_path: idbata1:1G;idbata2:1G;idbata3:1G;auto extend
   - 分布式性能优化
     - 主从

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
