//package oss;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
///**
// * @author zack <br>
// * @create 2020-10-10 13:21 <br>
// * @project project-ec <br>
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class OssTest {
//
//  @Resource private OSS ossClient;
//
//  @Value("${spring.cloud.alicloud.oss.bucket-name}")
//  private String bucketName;
//
//  @Test
//  public void testUpload() throws FileNotFoundException {
//    String endpoint = "oss-cn-hangzhou.aliyuncs.com";
//    String accessKeyId = "xx";
//    String accessKeySecret = "xx";
//    String bucketName = "project-ec";
//    String objectName = "谷粒商城-微服务架构图.jpg";
//
//    // 创建OSSClient实例。
//    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//    InputStream in = new FileInputStream("H:\\BaiduNetdiskDownload\\谷粒商城-微服务架构图.jpg");
//    ossClient.putObject(bucketName, objectName, in);
//
//    // 关闭OSSClient。
//    ossClient.shutdown();
//  }
//
//  @Test
//  public void testUploadByAliCloud() throws FileNotFoundException {
//
//    InputStream in = new FileInputStream("H:\\BaiduNetdiskDownload\\谷粒商城-微服务架构图.jpg");
//    ossClient.putObject(bucketName, "谷粒商城-微服务架构图.jpg", in);
//
//    // 关闭OSSClient。
//    ossClient.shutdown();
//  }
//}
