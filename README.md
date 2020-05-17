## project

### backend

1. introduce

   - this project is about seckill system, which is implement by java

2. technique

   - front
     - ~~thymeleaf~~: vue
     - bootstrap
     - ~~JQuery~~
   - backend
     - java
     - jsr303
     - mybatis
   - middleware
     - rabbitmq
     - redis
     - druid

3. feature

   - login
     - distribution session
     - password security: md5
     - jsr303 parameter validation
     - global exception handler
   - seckill
     - commodity list
     - commodity detail
     - order detail
   - jmter test
     - mock multi user
     - command
   - optimization
     - cache strategy: page cache + url cache + object cache
     - static page and front and backend separation
     - static resource
     - cdn
   - seckill api
     - cache strategy, reduce access to the database
     - memory mark, reduce access to the redis
     - use mq
     - use nginx horizontal expansion
   - api security
     - url hidden
     - use captcha
     - api limit

4. concurrent core

   - cache
   - async
   - distribution

---

## issue

1. `[application.yml]`druid monitor TODO: this is no work, now it works for DruidCinfig
