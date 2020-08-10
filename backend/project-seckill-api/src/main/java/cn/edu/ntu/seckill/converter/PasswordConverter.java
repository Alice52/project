package cn.edu.ntu.seckill.converter;

import cn.edu.ntu.seckill.model.po.PasswordPO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.utils.SecurityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.io.UnsupportedEncodingException;

/**
 * @author zack <br>
 * @create 2020-08-10 20:32 <br>
 * @project project-seckill <br>
 */
@Mapper
public interface PasswordConverter {

  PasswordConverter INSTANCE = Mappers.getMapper(PasswordConverter.class);

  /**
   * password column is special, so we need to convert it in outer.
   *
   * @param userVO
   * @return
   */
  @Mappings({
    @Mapping(source = "salt", target = "salt"),
    @Mapping(target = "password", expression = "java(buildPassword(userVO.getPassword(), salt))")
  })
  PasswordPO userVO2PasswordPO(UserVO userVO, String salt) throws UnsupportedEncodingException;

  /**
   * Build encry password by password and salt.
   *
   * @param password
   * @param salt
   * @return
   * @throws UnsupportedEncodingException
   */
  default String buildPassword(String password, String salt) throws UnsupportedEncodingException {
    return SecurityUtils.convertPassword(password, salt);
  }
}
