package cn.edu.ntu.seckill.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.digest.MD5;

import java.io.UnsupportedEncodingException;

/**
 * @author zack <br>
 * @create 2020-08-10 20:34 <br>
 * @project project-seckill <br>
 */
public final class SecurityUtils {

  /**
   * convert password to db data.
   *
   * @param password
   * @param salt
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String convertPassword(String password, String salt)
      throws UnsupportedEncodingException {
    MD5 md5 = new MD5(salt.getBytes(CharsetUtil.UTF_8));
    return md5.digestHex(password);
  }
}
