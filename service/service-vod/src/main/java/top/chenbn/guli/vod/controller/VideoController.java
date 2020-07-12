package top.chenbn.guli.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.exceptionhandler.GuliException;
import top.chenbn.guli.vod.service.VideoService;
import top.chenbn.guli.vod.util.ConstantVodUtils;
import top.chenbn.guli.vod.util.InitVodCilent;

import java.util.List;

/**
 * @author chbn
 * @create 2020-07-11 10:29
 */
@RestController
@RequestMapping("/edu/vod/video")
@CrossOrigin
public class VideoController {

  @Autowired private VideoService videoService;

  /**
   * 上传视频到阿里云
   *
   * @param file
   * @return 视频id
   */
  @PostMapping("/uploadAliyunVideo")
  public Result uploadAliyunVideo(MultipartFile file) {
    String videoId = videoService.uploadAliyunVideo(file);
    return Result.ok().data("videoId", videoId);
  }

  /**
   * 根据视频id删除阿里云视频
   *
   * @param id
   * @return
   */
  @DeleteMapping("/removeAliyunVideo/{id}")
  public Result removeAliyunVideo(@PathVariable String id) {
    try {
      // 初始化对象
      DefaultAcsClient client =
          InitVodCilent.initVodClient(
              ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
      // 创建删除视频request对象
      DeleteVideoRequest request = new DeleteVideoRequest();
      // 向request设置视频id
      request.setVideoIds(id);
      // 调用初始化对象的方法实现删除
      client.getAcsResponse(request);
      return Result.ok();
    } catch (Exception e) {
      e.printStackTrace();
      throw new GuliException(20001, "删除视频失败");
    }
  }
  // 删除多个阿里云视频的方法
  // 参数多个视频id  List videoIdList
  @DeleteMapping("/delete-batch")
  public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
    videoService.removeMoreAlyVideo(videoIdList);
    return Result.ok();
  }
}
