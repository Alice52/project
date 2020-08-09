package cn.edu.ntu.seckill.controller;

import cn.edu.ntu.seckill.annotation.swagger.GoodsApi;
import cn.edu.ntu.seckill.exception.ThirdPartyApiException;
import cn.edu.ntu.seckill.model.po.PasswordPO;
import cn.edu.ntu.seckill.model.vo.UserVO;
import cn.edu.ntu.seckill.repository.IPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020-07-19 15:23 <br>
 * @project backend <br>
 */
@GoodsApi
@RestController
@RequestMapping("/project-seckill")
@Slf4j
public class TestController extends BaseController {

  @Resource private IPasswordRepository passwordRepository;

  @Resource private HttpServletRequest httpServletRequest;

  @GetMapping("/log")
  public String log() {
    UserVO userVO = new UserVO();
    userVO.setId("415241");
    log.info("{}", userVO);
    return "hello project-seckill";
  }

  @GetMapping("/hello")
  public String hello() {
    PasswordPO passwordPO = passwordRepository.queryByUserId("5c0d7482da1411ea93d70242ac110006");
    log.info("test log");

    log.info("password model: {}", passwordPO);
    return "hello project-seckill";
  }

  @GetMapping("/exception")
  public String exception() throws Exception {
    log.error("test log...exception");
    throw new ThirdPartyApiException();
  }
}
