package ec.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.member.entity.MemberCollectSubjectEntity;

import java.util.Map;

/**
 * 会员收藏的专题活动
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
