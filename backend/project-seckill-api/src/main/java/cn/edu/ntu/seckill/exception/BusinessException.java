package cn.edu.ntu.seckill.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-08 18:34 <br>
 * @project project-seckill <br>
 */
public class BusinessException extends RuntimeException {

  public class SendEmailException extends RuntimeException {
    public SendEmailException() {}

    public SendEmailException(String email) {
      super(StrUtil.format("Send email to {} failed.", email));
    }
  }
}
