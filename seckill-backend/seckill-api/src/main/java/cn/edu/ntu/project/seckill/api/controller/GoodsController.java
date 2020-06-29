package cn.edu.ntu.project.seckill.api.controller;

import cn.edu.ntu.project.seckill.api.entities.SeckillUser;
import cn.edu.ntu.project.seckill.api.service.GoodsService;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-29 20:06 <br>
 * @project seckill-backend <br>
 */
@RestController
@Api
@Slf4j
public class GoodsController {

  @Resource GoodsService goodsService;

  @GetMapping("/goods")
  public List<GoodsVo> index() {

    List<GoodsVo> goodsVos = goodsService.list();

    return goodsVos;
  }

  @GetMapping("/goods/{gid}")
  public GoodsVo view(@PathVariable(value = "gid") String gid, SeckillUser user) {

    log.info("goods view api: user" + user);
    GoodsVo goodsVo = goodsService.detail(gid);

    return goodsVo;
  }
}
