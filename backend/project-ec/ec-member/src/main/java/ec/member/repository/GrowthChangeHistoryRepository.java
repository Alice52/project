package ec.member.repository;

import ec.member.entity.GrowthChangeHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成长值变化历史记录
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-06 12:14:35
 */
@Mapper
public interface GrowthChangeHistoryRepository extends BaseMapper<GrowthChangeHistoryEntity> {}
