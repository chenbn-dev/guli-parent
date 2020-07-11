package top.chenbn.guli.vod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.vod.service.VodService;

/**
 * @author chbn
 * @create 2020-07-11 10:29
 */
@RestController
@RequestMapping("/edu/vod/video")
@CrossOrigin
public class VodController {

  @Autowired private VodService vodService;

  /**
   * 上传视频到阿里云
   *
   * @param file
   * @return 视频id
   */
  @PostMapping("/uploadAliyunVideo")
  public Result uploadAliyunVideo(MultipartFile file) {
    String videoId = vodService.uploadAliyunVideo(file);
    return Result.ok();
  }
}
