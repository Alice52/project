package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.GoodsApi;
import cn.edu.ntu.seckill.converter.GoodsConverter;
import cn.edu.ntu.seckill.model.bo.GoodsBO;
import cn.edu.ntu.seckill.model.vo.GoodsVO;
import cn.edu.ntu.seckill.service.IGoodsService;
import cn.hutool.json.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-08-11 21:45 <br>
 * @project project-seckill <br>
 */
@RestController
@RequestMapping("/goods")
@GoodsApi
public class GoodsController extends BaseController {

  @Resource private IGoodsService goodsService;

  @PostMapping("/create")
  public JSON create(@RequestBody @Valid GoodsVO goodsVO) {

    GoodsBO goodsBO = GoodsConverter.INSTANCE.vo2bo(goodsVO);
    String goodsId = goodsService.create(goodsBO);

    return buildJson("id", goodsId);
  }

  @GetMapping("/list")
  public List<GoodsVO> index(
      @RequestParam("pageSize") Integer pageSize,
      @RequestParam("currentPage") Integer currentPage,
      @RequestParam(value = "searchKey", required = false) String searchKey) {

    return goodsService.list(pageSize, currentPage, searchKey);
  }

  @GetMapping("/{goodsId}")
  public GoodsVO view(@PathVariable("goodsId") @NotNull String goodsId) {

    return goodsService.getById(goodsId);
  }

  @GetMapping
  public GoodsVO viewByName(@RequestParam("goodsName") @NotNull String goodsName) {

    return goodsService.getByName(goodsName);
  }

  @PutMapping("/{goodsId}")
  public JSON update(
      @PathVariable("goodsId") @NotNull String goodsId,
      @RequestBody GoodsVO goodsVO,
      @RequestParam(value = "isFullScaleUpdate", defaultValue = "true") boolean isFullScaleUpdate) {

    GoodsBO goods = GoodsConverter.INSTANCE.vo2bo(goodsVO);
    goods.setId(goodsId);
    String id = goodsService.fullScaleUpdate(goods, isFullScaleUpdate);

    return buildJson("id", id);
  }
}
