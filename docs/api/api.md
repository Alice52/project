# API

[基本规则](https://github.com/inetfuture/technote/blob/master/restful_api.md#best-practices)

## 1. resources

### create

- endpoint

  **POST** /api/${modules}/${resources}

- request body

  ```json
  {
    "id": "5eb3f0ed44bc2902777c5732"
  }
  ```

- response body

  ```json
  {
    "id": "5eb3f0ed44bc2902777c5732"
  }
  ```

## 2. loggers

### 2.1 list

- endpoint

  **GET** /actuator/loggers

- request paramters

  ```json
  null
  ```

- response body

  ```json
  {
    "levels": ["OFF", "ERROR", "WARN", "INFO", "DEBUG", "TRACE"],
    "loggers": {
        "ROOT": {
            "configuredLevel": "INFO",
            "effectiveLevel": "INFO"
        },
        "com.itmuch.logging.TestController": {
            "configuredLevel": null,
            "effectiveLevel": "INFO"
        }
    }
  ```

### 2.2 view

- endpoint

  **GET** /actuator/loggers/loggers.cn.edu.ntu.seckill.aop.ControllerAspect

- request paramters

  ```json
  null
  ```

- response body

  ```json
  {
    "configuredLevel": null,
    "effectiveLevel": "DEBUG"
  }
  ```

### 2.3 update

- endpoint

  **POST** /actuator/loggers/loggers.cn.edu.ntu.seckill.aop.ControllerAspect

- request body

  ```json
  {
    "configuredLevel": "INFO"
  }
  ```

- response body
  - status: 204
  ```json
  null
  ```
