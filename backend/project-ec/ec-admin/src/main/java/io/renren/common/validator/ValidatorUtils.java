/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * <p>https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package io.renren.common.validator;

import cn.hutool.core.util.StrUtil;
import io.renren.common.exception.RRException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * hibernate-validator校验工具类
 *
 * <p>参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ValidatorUtils {

  private static final Pattern MOBILE_PATTERN = Pattern.compile("^[1]\\d{10}$");
  private static final Pattern EMAIL_PATTERN =
      Pattern.compile("^[_A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

  private static Validator validator;

  static {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  public static boolean validateMobile(String phoneNumber) {
    if (StrUtil.isEmpty(phoneNumber)) {
      return false;
    }
    Matcher m = MOBILE_PATTERN.matcher(phoneNumber);
    return m.matches();
  }

  public static boolean validateEmail(String email) {

    if (StrUtil.isEmpty(email)) {
      return false;
    }
    Matcher m = EMAIL_PATTERN.matcher(email);
    return m.matches();
  }
  /**
   * 校验对象
   *
   * @param object 待校验对象
   * @param groups 待校验的组
   * @throws RRException 校验不通过，则报RRException异常
   */
  public static void validateEntity(Object object, Class<?>... groups) throws RRException {
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
    if (!constraintViolations.isEmpty()) {
      StringBuilder msg = new StringBuilder();
      for (ConstraintViolation<Object> constraint : constraintViolations) {
        msg.append(constraint.getMessage()).append("<br>");
      }
      throw new RRException(msg.toString());
    }
  }
}
