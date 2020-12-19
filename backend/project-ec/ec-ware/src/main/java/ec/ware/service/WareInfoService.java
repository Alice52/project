package ec.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.ware.model.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
public interface WareInfoService extends IService<WareInfoEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
