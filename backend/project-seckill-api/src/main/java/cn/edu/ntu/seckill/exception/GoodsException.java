package cn.edu.ntu.seckill.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-11 22:38 <br>
 * @project project-seckill <br>
 */
public class GoodsException extends RuntimeException {

  public class GoodsNameDuplicateException extends RuntimeException {

    public GoodsNameDuplicateException() {}

    public GoodsNameDuplicateException(String goodsName) {

      super(StrUtil.format("Please change goods name: {}, and try again.", goodsName));
    }
  }

  public class GoodsNotExistenceException extends RuntimeException {

    public GoodsNotExistenceException() {}

    public GoodsNotExistenceException(Object object) {
      super(
          StrUtil.format(
              "Cannot find goods according to condition: {}, please check again.", object));
    }
  }
}
