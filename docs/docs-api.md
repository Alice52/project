### common

1. if call api success, will response data only
2. otherwise will response error message and status code will be 400

   ```json
   {
     "errorCode": xxxxxx,
     "errorMsg": "error message",
     "parameters": {
       "parameters1": "value1",
       "parameters2": "value2",
       "parameters3": "value3"
     }
   }
   ```

### detail

1. register

   - Endpoint: `{project-name}/user/register`
   - Method: POST
   - Request body

     ```json
     {
       "username": "zack",
       "password": "md5 by specify key, then backend will do md5 again with randon salt, which will be store in database"
     }
     ```

   - Reponse

     ```json
     {
       "user": "userId"
     }
     ```

2. login

   - Endpoint: `{project-name}/user/login`
   - Method: POST
   - Request body

     ```json
     {
       "username": "zack",
       "password": "md5 by specify key"
     }
     ```

   - Reponse

     ```json
     {
       "user": "userId"
     }
     ```

3. goods list

   - Endpoint: `{project-name}/goods`
   - Method: GET
   - Request paramters

     `null`

   - Reponse

     ```json
     [
       {
         "id": "uuid",
         "goodsName": "goods name",
         "goodsTitle": "goods title",
         "goodsImg": "goods logo",
         "goodsDetail": "goods detail",
         "goodsPrice": "goods origin price",
         "goodsStock": "goods stock",
         "seckillPrice": "goods seckill price",
         "stockCount": "seckill goods stock count",
         "startDate": "seckill goods start Date",
         "endDate": "seckill goods end Date"
       }
     ]
     ```

4. goods detail

   - Endpoint: `{project-name}/goods/{uid}`
   - Method: GET
   - Request paramters

     `null`

   - Reponse

     ```json
     {
       "id": "uuid",
       "goodsName": "goods name",
       "goodsTitle": "goods title",
       "goodsImg": "goods logo",
       "goodsDetail": "goods detail",
       "goodsPrice": "goods origin price",
       "goodsStock": "goods stock",
       "seckillPrice": "goods seckill price",
       "stockCount": "seckill goods stock count",
       "startDate": "seckill goods start Date",
       "endDate": "seckill goods end Date"
     }
     ```

5. seckill

   - Endpoint: `{project-name}/seckill/goods/{uid}`
   - Method: POST
   - Request body

   `null`

   - Reponse

     ```js
     {
       "success": boolean,
       "goodsId": "uid",
       "orderId": "mapping order id if success"
     }
     ```

6. seckill

   - Endpoint: `{project-name}/seckill/goods/reset`
   - Method: POST
   - Request body

   `null`

   - Reponse

     ```js
     {
       "success": boolean
     }
     ```

7. order

   - Endpoint: `{project-name}/order/{id}`
   - Method: GET
   - Request parameter

   `null`

   - Reponse

     ```js
     {
        "id": "uid",
        "userId": "uid",
        "goodsId": "uid",
        "deliveryAddrId": "uid",
        "goodsName": "name",
        "goodsCount": int,
        "goodsPrice": decimal,
        "orderChannel": "phone/pc",
        "status": "0: 新建未支付; 1: 已支付; 2: 已发货; 3: 已收货; 4: 已退款; 5: 已完成";
        "createDate": datetime,
        "payDate": datetime
     }
     ```
