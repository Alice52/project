package ec.member.converter;

import ec.member.entity.MemberLevelEntity;
import ec.member.model.vo.MemberLevelVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zack <br>
 * @create 2020/12/12 <br>
 * @project project-ec <br>
 */
@Mapper
public interface MemberLevelConverter {
  MemberLevelConverter INSTANCE = Mappers.getMapper(MemberLevelConverter.class);

  /**
   * Convert vo to po.
   *
   * @param vo
   * @return MemberLevelEntity
   */
  MemberLevelEntity vo2po(MemberLevelVO vo);

  /**
   * Convert po to vo.
   *
   * @param po
   * @return MemberLevelVO
   */
  MemberLevelVO po2vo(MemberLevelEntity po);
}
