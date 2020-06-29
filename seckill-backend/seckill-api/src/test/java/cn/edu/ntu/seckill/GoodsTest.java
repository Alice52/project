package cn.edu.ntu.seckill;

import cn.edu.ntu.project.seckill.api.SecKillApplication;
import cn.edu.ntu.project.seckill.api.controller.GoodsController;
import cn.edu.ntu.project.seckill.api.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2020-06-29 20:35 <br>
 * @project seckill-backend <br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecKillApplication.class)
@WebAppConfiguration
@Slf4j
public class GoodsTest {

  @Resource GoodsController controller;

  @Test
  public void testIndex() {
    List<GoodsVo> goodsVos = controller.index();

    goodsVos.stream().forEach(x -> log.info(String.valueOf(x)));
  }

  @Test
  public void testDetail() {
    GoodsVo goodsVo = controller.view("1", null);

    log.info(String.valueOf(goodsVo));
  }
}
