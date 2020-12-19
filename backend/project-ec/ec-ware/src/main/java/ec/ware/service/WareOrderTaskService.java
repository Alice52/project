package ec.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ec.common.utils.PageUtils;
import ec.ware.model.entity.WareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

  PageUtils queryPage(Map<String, Object> params);
}
