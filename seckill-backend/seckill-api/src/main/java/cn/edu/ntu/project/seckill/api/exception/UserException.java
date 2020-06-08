package cn.edu.ntu.project.seckill.api.exception;

/**
 * @author zack <br>
 * @create 2020-06-08 19:50 <br>
 * @project seckill-backend <br>
 */
public class UserException extends RuntimeException {
  public class UserAlreadyExistenceException extends RuntimeException {}

  public class UserNotExistenceException extends RuntimeException {}

  public class UserLoginException extends RuntimeException {}
}
