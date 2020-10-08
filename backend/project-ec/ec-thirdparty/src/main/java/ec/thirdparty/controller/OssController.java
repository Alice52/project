package ec.thirdparty.controller;

import ec.common.utils.R;
import ec.thirdparty.IOssService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-10-10 14:38 <br>
 * @project project-ec <br>
 */
@Api
@RestController
@RequestMapping("/thirdparty/oss")
public class OssController {

  @Resource private IOssService ossService;

  @GetMapping("/test")
  public void testUploadByAliCloud() throws FileNotFoundException {

    // obtain file-content from parameter
    ossService.upload("file-name", new FileInputStream(""));
  }

  @GetMapping("/signature")
  public R signature() throws UnsupportedEncodingException {

    final Map<String, String> signature = ossService.signature();

    return R.ok().put("data", signature);
  }
}
