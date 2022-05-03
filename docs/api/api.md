# API

[基本规则](https://github.com/inetfuture/technote/blob/master/restful_api.md#best-practices)

## 资源名

### 创建

- Endpoint

  **POST** /api/${version}/${模块名}/${资源名复数}

- 请求参数

  ```json
  {
      ...
  }
  ```

- 响应结果

  ```json
  {
    id: ObjectId;
  }
  ```

---

---

---

## 举例 - 开放 API 文档

1. host
   - test env: maimaieqishuostgapi.mcdchina.net
   - prod env: maimaieqishuoapi.mcdchina.net
2. protocol: https
3. env info variables

   | appid |   appsecret    |
   | :---: | :------------: |
   |  xxx  | 5231\*\*\*\*03 |

4. response explain

   - struct
     ```json
     {
       "code": 0,
       "msg": "success",
       "data": object
     }
     ```
   - code explain

     | code |       explain        |
     | :--: | :------------------: |
     |  0   |       请求成功       |
     |  1   | 请求错误, 详情见 msg |

### 签名: 只是方便测试，代码中不要调用【debug api】

#### 获取

1. endpoint

   **POST** /post/openapi/signature

2. request header

   |     name     | required |               explain                |              sample              |
   | :----------: | :------: | :----------------------------------: | :------------------------------: |
   | Content-Type |    Y     |                  --                  |  application/json;charset=UTF-8  |
   |  x-noncestr  |    Y     |   random string and for signature    | bee5639487e49d5306a303262708ec95 |
   | x-timestamp  |    Y     |  13 bit timestamp and for signature  |        **1649395025000**         |
   |   x-appid    |    Y     |       appid and for signature        |            e-arrival             |
   |     uri      |    Y     | **Endpoint value** and for signature |       /post/openapi/posts        |

3. request body

   ```json
   {
     "postType": 0,
     "contentType": 0
   }
   ```

4. response

   ```json
   {
     "code": 0,
     "msg": "success",
     "data": "b8e0faefa7d6cfc0d9fca8951075bacc"
   }
   ```

5. **explain signature flow**
   - build list which contains these filed: uri, bodyStr, timestampStr, noncestr, appId, appSecret: _and notice the sequence_
   - then convert list to json string, and do md5 to get signature.

### 帖子

#### 创建

1. Endpoint

   **POST** /post/openapi/posts

2. request header

   |     name     | required |             explain             |              sample              |
   | :----------: | :------: | :-----------------------------: | :------------------------------: |
   | Content-Type |    Y     |               --                |  application/json;charset=UTF-8  |
   | x-signature  |    Y     |        request signature        | bee5639487e49d5306a303262708ec95 |
   |  x-noncestr  |    Y     |               --                | 6a303262708ec95d5306a3062708ec95 |
   | x-timestamp  |    Y     | **must be within five minutes** |        **1649395025000**         |
   |   x-appid    |    Y     |               --                |            e-arrival             |

3. request body

   ```json
   {
     "employeeNo": "15301379", // required-员工编号
     "coverImg": "http://img.netbian.com/file/2022/0408/small003126aXpDF1649349086.jpg", // required-封面图
     // required
     "attachments": [
       {
         "attachmentUrl": "http://img.netbian.com/file/2022/0403/003154ZBed6.jpg", // required-附件图片地址
         "mediaType": 1 //    附件类型: IMAGE-1[只支持图片]; VIDEO-2
       }
     ],
     "postType": 0, // required-帖子类型: 0-帖子[只支持帖子]; 1-视频
     "publishStatus": 1, // 帖子上架状态: 0=draft，1=published
     "circleId": 6, // 圈子ID: 只能是6或者不填
     "isCircle": true, // 圈子ID相关
     "title": "sas", // 帖子标题
     "content": "sasa" // 帖子内容
   }
   ```

4. response

   - success

     ```json
     {
       "code": 0,
       "msg": "success",
       "data": {
         "id": 26, // 帖子Id
         "publishStatus": 1 // 帖子发布状态
       }
     }
     ```

   - erro code

     |   code    |      explain       |
     | :-------: | :----------------: |
     | 400000001 |   签名非法或过期   |
     | 400000002 | 帖子文字涉及敏感字 |

5. others
   - FYI1: this api will be limited with 200qps due to time consumed.
   - **FYI2**: the value of `x-timestamp` in header **must be** within five minutes

---

## reference

1. [md5 link online](https://emn178.github.io/online-tools/md5.html)
