package cn.edu.ntu.project.seckill.api.exception;

/**
 * @author zack <br>
 * @create 2020-06-30 21:24 <br>
 * @project seckill-backend <br>
 */
public class SecKillException extends RuntimeException {

  public class SecKillGoodsOverException extends RuntimeException {}

  public class RepeatedSecKillException extends RuntimeException {}
}
