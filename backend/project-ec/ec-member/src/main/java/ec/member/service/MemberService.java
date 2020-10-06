package ec.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
public interface MemberService extends IService<MemberEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
