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
