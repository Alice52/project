package ec.product.controller;

import ec.common.utils.PageUtils;
import ec.common.utils.R;
import ec.product.entity.CommentReplayEntity;
import ec.product.service.CommentReplayService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:31:27
 */
@Api
@RestController
@RequestMapping("product/commentreplay")
public class CommentReplayController {
  @Resource private CommentReplayService commentReplayService;

  @GetMapping("/list")
  // @RequiresPermissions("product:commentreplay:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = commentReplayService.queryPage(params);

    return R.ok().put("page", page);
  }

  @GetMapping("/info/{id}")
  // @RequiresPermissions("product:commentreplay:info")
  public R info(@PathVariable("id") Long id) {
    CommentReplayEntity commentReplay = commentReplayService.getById(id);

    return R.ok().put("commentReplay", commentReplay);
  }

  @PostMapping("/save")
  // @RequiresPermissions("product:commentreplay:save")
  public R save(@RequestBody CommentReplayEntity commentReplay) {
    commentReplayService.save(commentReplay);

    return R.ok();
  }

  @PutMapping("/update/{id}")
  // @RequiresPermissions("product:commentreplay:update")
  public R update(@PathVariable("id") Long id, @RequestBody CommentReplayEntity commentReplay) {
    commentReplay.setId(id);
    commentReplayService.updateById(commentReplay);

    return R.ok();
  }

  @DeleteMapping("/delete")
  // @RequiresPermissions("product:commentreplay:delete")
  public R delete(@RequestBody Long[] ids) {
    commentReplayService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

  @DeleteMapping("/delete/{id}")
  // @RequiresPermissions("product:commentreplay:delete")
  public R deleteById(@PathVariable("id") Long id) {
    commentReplayService.removeById(id);

    return R.ok();
  }
}
