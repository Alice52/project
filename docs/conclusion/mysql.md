### mysql 服务器优化

![avatar](/docs/static/mysql-standalone.png)

1. mysql 应用性能优化: `缓存 + 异步 + 批处理`

   - 写操作: 批量
   - 读操作: 索引

2. 单机配置性能优化

   - `max_connection`: 1000 default 100
   - `innodb_file_per_table`: 1
   - `innodb_buffer_pool_size`: 60-80% 的内存
   - `innodb_log_file_size`: 256M
   - `innodb_log_buffer_size`: 16M
   - `innodb_flush_log_at_trx_commit`: 2
     - 0: 表示写入 redo/undo log 的 buffer 中就算成功[可能会丢失 1 秒的数据]
     - 1: 表示每次书屋提交都会将数据 flush 到磁盘才算成功[真正的 acid]
     - 2: 表示写入 redo/undo log 的 buffer 中且调用操作系统的 write 方法就算成功[write 方法之后数据被操作系统接管, 会在关机之前写入磁盘]
   - `innodb_data_file_path`: idbata1:1G;idbata2:1G;idbata3:1G;auto extend

3. 主从性能优化

   - bin log: 事务提交之后才会有且每个事务都会有
   - 主从: 开启 `bin log` + `设置主从让步账号, 配置主从同步`
   - 问题: 从库永远比主库慢 + `redo/undo log` 成功之后 mysql 就会认为这个 transaction 成功, 之后的 `bin log buffer` + `bin log` 不一定能成功
   - 半同步: 保证主从数据强一致[当至少有一个从库写入成功后整个操作才算完成]

4. 多主库问题

   - 数据分片
   - 分片维度
   - 分片冗余一致性
   - 无迁移扩展

5. 分布式事务的二阶段提交

![avatar](/docs/static/mysql-cluster.png)
