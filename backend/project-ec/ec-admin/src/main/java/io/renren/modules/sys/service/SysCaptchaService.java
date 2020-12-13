/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * <p>https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.SysCaptchaEntity;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysCaptchaService extends IService<SysCaptchaEntity> {

  /** 获取邮箱验证码 */
  void sendCaptcha(String uuid, String username) throws Exception;

  /**
   * 验证码效验
   *
   * @param uuid uuid
   * @param code 验证码
   * @return true：成功 false：失败
   */
  boolean validate(String uuid, String code);
}
