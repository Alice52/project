/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * <p>https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtils;
import io.renren.modules.email.IMailSenderService;
import io.renren.modules.sys.dao.SysCaptchaDao;
import io.renren.modules.sys.entity.SysCaptchaEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysCaptchaService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaDao, SysCaptchaEntity>
    implements SysCaptchaService {
  @Autowired private Producer producer;
  @Resource private SysUserService sysUserService;

  @Resource private IMailSenderService mailSenderService;

  @Override
  public boolean validate(String uuid, String code) {
    SysCaptchaEntity captchaEntity =
        this.getOne(new QueryWrapper<SysCaptchaEntity>().eq("uuid", uuid));
    if (captchaEntity == null) {
      return false;
    }

    // 删除验证码
    this.removeById(uuid);

    if (captchaEntity.getCode().equalsIgnoreCase(code)
        && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
      return true;
    }

    return false;
  }

  @Override
  public void sendCaptcha(String uuid, String username) throws Exception {
    if (StringUtils.isBlank(uuid)) {
      throw new RRException("uuid不能为空");
    }
    // 生成文字验证码
    String code = producer.createText();
    SysCaptchaEntity captchaEntity = new SysCaptchaEntity();
    captchaEntity.setUuid(uuid);
    captchaEntity.setCode(code);
    // 5分钟后过期
    captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
    this.save(captchaEntity);

    // query by username
    SysUserEntity entity = sysUserService.queryByUserName(username);

    boolean success =
        mailSenderService.sendSimpleMailMessage(
            entity.getEmail(), "EC-ADMIN Login Validation Code", code);
    log.debug("sendCaptcha(): " + code);
  }
}
