package cn.edu.ntu.seckill.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2020-08-08 18:34 <br>
 * @project project-seckill <br>
 */
public class BusinessException extends RuntimeException {

  public BusinessException() {}

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(Exception e) {
    super(e);
  }

  public class SendEmailException extends BusinessException {
    public SendEmailException() {}

    public SendEmailException(String email) {
      super(StrUtil.format("Send email to {} failed.", email));
    }
  }

  public class RateLimitException extends BusinessException {
    public RateLimitException() {}

    public RateLimitException(String email) {
      super(StrUtil.format("Rate limit error: {}, please wait and retry again.", email));
    }
  }

  public class SeckillGoodsStock extends BusinessException {
    public SeckillGoodsStock() {}

    public SeckillGoodsStock(String message) {
      super(StrUtil.format("Seckill goods stock exception: {}.", message));
    }
  }

  public class CaptchaInvalidException extends BusinessException {
    public CaptchaInvalidException() {}

    public CaptchaInvalidException(String captchaCode) {
      super(
          StrUtil.format(
              "captcha code {} is invalid, please check and re-try again.", captchaCode));
    }
  }

  public class GenerateTokenException extends BusinessException {
    public GenerateTokenException() {}
  }
}
