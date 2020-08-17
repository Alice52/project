package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.GoodsApi;
import cn.edu.ntu.seckill.converter.SeckillGoodsConverter;
import cn.edu.ntu.seckill.model.bo.SeckillGoodsBO;
import cn.edu.ntu.seckill.model.vo.ListVO;
import cn.edu.ntu.seckill.model.vo.SeckillGoodsVO;
import cn.edu.ntu.seckill.service.ISeckillGoodsService;
import cn.hutool.json.JSON;
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
@RequestMapping("/seckill-goods")
@GoodsApi
public class SeckillGoodsController extends BaseController {

  @Resource private ISeckillGoodsService seckillGoodsService;

  @PostMapping("/publish-promo")
  public JSON create(@RequestBody @Valid SeckillGoodsVO goodsVO) {

    SeckillGoodsBO goodsBO = SeckillGoodsConverter.INSTANCE.vo2bo(goodsVO);
    String goodsId = seckillGoodsService.publishPromo(goodsBO);

    return buildJson("id", goodsId);
  }

  @GetMapping("/list")
  public ListVO<SeckillGoodsVO> index(
      @RequestParam(value = "pageSize", required = false) Integer pageSize,
      @RequestParam(value = "currentPage", required = false) Integer currentPage,
      @RequestParam(value = "searchKey", required = false) String searchKey) {

    return seckillGoodsService.list(pageSize, currentPage, searchKey);
  }

  @GetMapping("/{goodsId}")
  public SeckillGoodsVO view(@PathVariable("goodsId") @NotNull String goodsId) {

    return seckillGoodsService.view(goodsId);
  }
}
