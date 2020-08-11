package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.GoodsApi;
import cn.edu.ntu.seckill.converter.GoodsConverter;
import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.vo.GoodsVO;
import cn.edu.ntu.seckill.service.IGoodsService;
import cn.hutool.json.JSON;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020-08-11 21:45 <br>
 * @project project-seckill <br>
 */
@RestController
@RequestMapping("/goods")
@GoodsApi
@Validated
public class GoodsController extends BaseController {

  @Resource private IGoodsService goodsService;

  @PostMapping("/create")
  public JSON create(@RequestBody @Valid GoodsVO goodsVO) {

    GoodsBO goodsBO = GoodsConverter.INSTANCE.vo2bo(goodsVO);

    String goodsId = goodsService.create(goodsBO);

    return buildJson("id", goodsId);
  }

  @PostMapping("/{goodsId}")
  public GoodsVO view(@PathVariable("goodsId") @NotNull String goodsId) {

    GoodsVO goodsVO = goodsService.getById(goodsId);

    return goodsVO;
  }

  @PutMapping("/{goodsId}")
  public JSON update(
      @PathVariable("goodsId") @NotNull String goodsId, @RequestBody @Valid GoodsVO goodsVO) {

    GoodsBO goods = GoodsConverter.INSTANCE.vo2bo(goodsVO);
    goods.setId(goodsId);
    String id = goodsService.update(goods);

    return buildJson("id", id);
  }
}
