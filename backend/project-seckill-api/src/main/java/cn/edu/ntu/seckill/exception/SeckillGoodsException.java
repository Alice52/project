package cn.edu.ntu.seckill.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-11 22:38 <br>
 * @project project-seckill <br>
 */
public class SeckillGoodsException extends RuntimeException {

  public class SeckillGoodsDuplicateException extends RuntimeException {

    public SeckillGoodsDuplicateException() {}

    public SeckillGoodsDuplicateException(String goodsName) {

      super(StrUtil.format("Please change goods: {}, and try again.", goodsName));
    }
  }

  public class SeckillGoodsNotExistenceException extends RuntimeException {

    public SeckillGoodsNotExistenceException() {}

    public SeckillGoodsNotExistenceException(Object object) {
      super(
          StrUtil.format(
              "Cannot find goods according to condition: {}, please check again.", object));
    }
  }
}
