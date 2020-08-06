package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.model.vo.UserVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

/**
 * @author zack <br>
 * @create 2020-07-26 19:06 <br>
 * @project project-seckill <br>
 */
@Validated
public interface IUserService {

  /**
   * Register a new user.
   *
   * @param userVO
   * @return
   * @throws UnsupportedEncodingException
   */
  String register(@NotNull UserVO userVO) throws UnsupportedEncodingException;
}
