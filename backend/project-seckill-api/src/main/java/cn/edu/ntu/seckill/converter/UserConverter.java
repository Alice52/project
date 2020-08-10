package cn.edu.ntu.seckill.converter;

import cn.edu.ntu.seckill.model.bo.UserBO;
import cn.edu.ntu.seckill.model.po.UserPO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-08-10 19:54 <br>
 * @project project-seckill <br>
 */
@Mapper(imports = {LocalDateTime.class})
public interface UserConverter {

  UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

  /**
   * Convert user vo to bo.
   *
   * @param vo
   * @return
   * @throws Exception
   */
  UserBO vo2bo(UserVO vo) throws Exception;

  /**
   * Convert user vo to po.
   *
   * @param vo
   * @return
   */
  @Mapping(target = "registeredDate", expression = "java(LocalDateTime.now())")
  UserPO vo2po(UserVO vo);

  /**
   * Convert user bo to po.
   *
   * @param bo
   * @return
   */
  UserPO bo2po(UserBO bo);

  /**
   * Convert user bo to po.
   *
   * @param po
   * @return
   */
  UserBO po2bo(UserPO po);

  /**
   * Convert user po to vo.
   *
   * @param po
   * @return
   */
  UserVO po2vo(UserPO po);

  /**
   * Convert user bo to vo.
   *
   * @param bo
   * @return
   */
  UserVO bo2vo(UserBO bo);
}
