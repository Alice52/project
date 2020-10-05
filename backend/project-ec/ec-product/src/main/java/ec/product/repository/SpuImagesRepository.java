package ec.product.repository;

import ec.product.entity.SpuImagesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spu图片
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 21:28:34
 */
@Mapper
public interface SpuImagesRepository extends BaseMapper<SpuImagesEntity> {}
