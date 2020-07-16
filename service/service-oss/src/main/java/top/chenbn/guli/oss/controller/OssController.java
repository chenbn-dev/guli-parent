package top.chenbn.guli.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.oss.service.OssService;

/**
 * @author chbn
 * @create 2020-06-30 23:01
 */
@RestController
@RequestMapping("/edu/oss/file")
@CrossOrigin
public class OssController {

  @Autowired private OssService ossService;

  // 上传头像的方法
  @PostMapping
  public Result uploadOssFile(MultipartFile file) {
    // 获取上传文件 MultipartFile
    // 返回上传到oss的路径
    String url = ossService.uploadFileAvatar(file);
    return Result.ok().data("url", url);
  }
}
