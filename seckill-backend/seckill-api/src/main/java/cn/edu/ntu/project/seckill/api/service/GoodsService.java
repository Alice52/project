package cn.edu.ntu.project.seckill.api.service;

import cn.edu.ntu.project.seckill.api.entities.Goods;
import cn.edu.ntu.project.seckill.api.repository.IGoodsRepository;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-01 23:26 <br>
 * @project seckill-backend <br>
 */
@Service
public class GoodsService implements IGoodsService {

  @Resource IGoodsRepository goodsRepository;

  @Override
  public List<GoodsVo> list() {
    return goodsRepository.list();
  }

  @Override
  public GoodsVo detail(String gid) {

    // validate gid
   return goodsRepository.detail(gid);
  }
}
