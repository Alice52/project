## project-seckill

### technique

1. MySQL
2. Redis
3. rocket mq
4. spring boot
5. open resty
6. cdn
7. mybatis

### backend

1. 分布式

   - nginx 反向代理
   - 分布式会话管理

2. 缓存

   - redis 缓存
   - 本地缓存
   - 热点 nginx LUA 缓存

3. 页面静态化

   - cdn
   - 动态请求缓存
   - 页面静态化

4. 下单

   - 缓存库存: `缓存击穿`
   - 下单异步化
   - 异步事务

5. 流量

   - 秒杀令牌
   - 秒杀大闸
   - 推列泄洪

6. 防刷限流

   - 验证码
   - 限流器
   - 防黄牛

7. 性能测试

   - jemter 压测
   - 优化

### frontend

### DB Design

1. 可以把 stock 从 goods 表中单独拆分出来
   - 对这个字段进行读取, innodb 的行锁会阻塞其他线程对整条纪录的读取[有些线程读取的仅仅是商品本身的信息]
   - 可以拆分成单独的表, 就解决了这个问题, 而拆成小表之后读取速度会相应的变快
2. 可以把 password 从 user 表中单独拆分出来
   - same as 1.

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
