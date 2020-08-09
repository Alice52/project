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

    public UserNotExistenceException(String condition, String message) {
      super(StrUtil.format("Cannot find user by `{}` condition `{}`.", message, condition));
    }
  }

  public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {}

    public InvalidTokenException(String message) {
      super(message);
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

  public class RegisterException extends RuntimeException {

    public RegisterException() {}

    public RegisterException(String message) {
      super(message);
    }
  }

  public class InvalidPassword extends RuntimeException {

    public InvalidPassword() {}

    public InvalidPassword(String message) {
      super(message);
    }
  }
}
