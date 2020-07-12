package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.client.VodClient;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.entity.EduVideo;
import top.chenbn.guli.exceptionhandler.GuliException;
import top.chenbn.guli.service.EduVideoService;

/**
 * 课程视频 前端控制器
 *
 * @author chenbn
 * @since 2020-07-04
 */
@RestController
@RequestMapping("/edu/service/video")
@CrossOrigin
public class EduVideoController {

  @Autowired private EduVideoService videoService;

  @Autowired private VodClient vodClient;
  /**
   * 添加小节
   *
   * @param eduVideo
   * @return
   */
  @PostMapping("/addVideo")
  public Result addVideo(@RequestBody EduVideo eduVideo) {
    videoService.save(eduVideo);
    return Result.ok();
  }

  /**
   * 修改小节
   *
   * @param eduVideo
   * @return
   */
  @PostMapping("/updateVideo")
  public Result updateVideo(@RequestBody EduVideo eduVideo) {
    videoService.updateById(eduVideo);
    return Result.ok();
  }

  /**
   * 删除小节并删除对应的阿里云视频
   *
   * @param id
   * @return
   */
  @DeleteMapping("/remove/{id}")
  public Result remove(@PathVariable String id) {
    // 根据小节id获取到视频id
    EduVideo eduVideo = videoService.getById(id);
    String videoSourceId = eduVideo.getVideoSourceId();
    // 判断小节里是否有视频id
    if (!StringUtils.isEmpty(videoSourceId)) {
      // 根据视频id，远程调用实现视频删除
      Result result = vodClient.removeAliyunVideo(videoSourceId);
      if (result.getCode() == 20001) {
        throw new GuliException(20001, "视频删除失败，熔断");
      }
    }
    videoService.removeById(id);
    return Result.ok();
  }
}
