package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.exception.UserException;
import cn.edu.ntu.project.seckill.api.repository.IUserRepository;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-06-08 19:41 <br>
 * @project seckill-backend <br>
 */
@Service
public class UserService implements IUserService {

  @Resource private IUserRepository userRepository;

  @Override
  public SeckillUser getByNickname(String nickname) {
    return userRepository.getByNickname(nickname);
  }

  @Override
  public int validateAndSave(SeckillUser user) {
    SeckillUser userPO = this.getByNickname(user.getNickname());

    if (null != userPO) {
      throw new UserException().new UserAlreadyExistenceException();
    }

    user.setId(IdUtil.fastUUID());
    user.setRegisterDate(LocalDateTime.now());
    user.setSalt(IdUtil.simpleUUID());
    user.setPassword(MD5.create().digestHex16(user.getPassword()));

    return userRepository.insert(user);
  }

  @Override
  public boolean doLogin(SeckillUser user) {

    String password = MD5.create().digestHex16(user.getPassword());
    String username = user.getNickname();

    SeckillUser userPO = userRepository.validateLogin(username, password);

    if (null != userPO) {
      return true;
    }

    return false;
  }
}
