package cn.edu.ntu.projectname.controller;

import cn.edu.ntu.model.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zack <br>
 * @create 2020-07-19 15:23 <br>
 * @project backend <br>
 */
@Api
@RestController
@RequestMapping("/project-name")
@ApiResponses({
  @ApiResponse(code = 400, message = "Internal Error", response = ErrorResponse.class)
})
@Slf4j
public class TestController {

  @GetMapping("/hello")
  public String hello() {
    log.info("test log");
    return "hello project-name";
  }
}
