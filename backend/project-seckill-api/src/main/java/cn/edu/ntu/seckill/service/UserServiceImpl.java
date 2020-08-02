package cn.edu.ntu.seckill.service;

import cn.edu.ntu.seckill.model.po.UserPO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.repository.IUserRepository;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import com.google.common.base.Utf8;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-07-26 19:10 <br>
 * @project project-seckill <br>
 */
@Service
public class UserServiceImpl implements IUserService {

  @Resource private IUserRepository userRepository;

  @Override
  public String register(@NotNull UserVO userVO) throws UnsupportedEncodingException {

    UserPO userPO = convert2PO(userVO);
    userRepository.create(userPO);

    return userPO.getId();
  }

  private static UserPO convert2PO(UserVO userVO) throws UnsupportedEncodingException {

    @Valid UserPO userPO = new UserPO();
    BeanUtil.copyProperties(userVO, userPO);
    userPO.setRegisterDate(LocalDateTime.now());
    userPO.setSalt(IdUtil.simpleUUID());
    String password = convertPassword(userVO.getPassword(), userPO.getSalt());
    userPO.setPassword(password);
    // this is do in db layer.
    // userPO.setId(IdUtil.simpleUUID());

    return userPO;
  }

  private static String convertPassword(String password, String salt)
      throws UnsupportedEncodingException {
    MD5 md5 = new MD5(salt.getBytes(CharsetUtil.UTF_8));
    return md5.digestHex(password);
  }

  private static UserVO convert2PO(UserPO userPO) {

    UserVO userVO = new UserVO();
    BeanUtil.copyProperties(userPO, userVO);
    userVO.setId(userPO.getId());

    return userVO;
  }
}
