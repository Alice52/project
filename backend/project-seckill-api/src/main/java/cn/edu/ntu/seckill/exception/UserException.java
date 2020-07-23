package cn.edu.ntu.seckill.exception;

/**
 * @author zack <br>
 * @create 2020-07-19 16:04 <br>
 * @project backend <br>
 */
public class UserException extends RuntimeException {
  public class UserAlreadyExistenceException extends RuntimeException {}

  public class UserNotExistenceException extends RuntimeException {}

  public class UserLoginException extends RuntimeException {

    public UserLoginException() {}

    public UserLoginException(String message) {
      super(message);
    }
  }
}
