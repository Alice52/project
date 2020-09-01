## 分布式会话管理

1. 连接: keep-alive 问题
2. token 续期
3. token 安全: ~~放入自定义的 header 中~~/使用自定义协议
4. sso:

   - 同域名

   ![avatar](/docs/static/sso-same-domain.png)

   - 根域名相同子域名不同

   ![avatar](/docs/static/sso-same-top-domain.png)

   - 域名都不同

   ![avatar](/docs/static/sso-diff.png)
