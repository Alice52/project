package ec.member.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ec.member.entity.MemberReceiveAddressEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员收货地址
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Mapper
public interface MemberReceiveAddressRepository extends BaseMapper<MemberReceiveAddressEntity> {}
