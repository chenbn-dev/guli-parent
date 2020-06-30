package top.chenbn.guli.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.oss.service.OssService;
import top.chenbn.guli.oss.util.ConstantPropertiesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chbn
 * @create 2020-06-30 23:02
 */
@Service
public class OssServiceImpl implements OssService {
  @Override
  public String uploadFileAvatar(MultipartFile file) {
    // 获取阿里云存储相关常量
    String endPoint = ConstantPropertiesUtil.END_POINT;
    String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
    String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
    String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
    try {
      // 创建OSS实例。
      OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
      // 获取上传文件输入流
      InputStream inputStream = file.getInputStream();
      // 获取文件名称
      String fileName = file.getOriginalFilename();
      // 1 在文件名称里面添加随机唯一的值
      //      String uuid = UUID.randomUUID().toString().replaceAll("-", "");
      String id = System.currentTimeMillis() + "";
      String fileType = fileName.substring(fileName.lastIndexOf("."));
      fileName = id + fileType;
      // yuy76t5rew01.jpg
      //      fileName = uuid+fileName;
      // 2 把文件按照日期进行分类
      // 获取当前日期
      //   2019/11/12
      String datePath = new DateTime().toString("yyyy/MM/dd");
      // 拼接
      //  2019/11/12/ewtqr313401.jpg
      fileName = datePath + "/" + fileName;
      // 调用oss方法实现上传
      // 第一个参数  Bucket名称
      // 第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
      // 第三个参数  上传文件输入流
      ossClient.putObject(bucketName, fileName, inputStream);
      // 关闭OSSClient。
      ossClient.shutdown();
      // 把上传之后文件路径返回
      // 需要把上传到阿里云oss路径手动拼接出来
      //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg
      String url = "https://" + bucketName + "." + endPoint + "/" + fileName;
      return url;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
