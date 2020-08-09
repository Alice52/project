package cn.edu.ntu.seckill.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-07-19 16:04 <br>
 * @project backend <br>
 */
public class UserException extends RuntimeException {
  public class UserAlreadyExistenceException extends RuntimeException {
    public UserAlreadyExistenceException() {}

    public UserAlreadyExistenceException(String message) {
      super(
          StrUtil.format(
              "Cannot register due to duplicated `{}`, please check and try again.", message));
    }
  }

  public class UserNotExistenceException extends RuntimeException {

    public UserNotExistenceException() {}

    public UserNotExistenceException(String message) {
      super(StrUtil.format("Cannot find user by `{}` condition.", message));
    }
  }

  public class UserLoginException extends RuntimeException {

    public UserLoginException() {}

    public UserLoginException(String message) {
      super(message);
    }
  }

  public class InvalidValidationCodeException extends RuntimeException {

    public InvalidValidationCodeException() {}

    public InvalidValidationCodeException(String message) {
      super(message);
    }
  }
}
