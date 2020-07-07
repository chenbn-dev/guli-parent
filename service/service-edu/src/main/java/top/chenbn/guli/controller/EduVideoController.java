package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.entity.EduVideo;
import top.chenbn.guli.service.EduVideoService;

/**
 * 课程视频 前端控制器
 *
 * @author chenbn
 * @since 2020-07-04
 */
@RestController
@RequestMapping("/guli/edu/video")
@CrossOrigin
public class EduVideoController {

  @Autowired private EduVideoService videoService;

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
  // TODO 这个方法后面需要完善：删除小节的时候，同时把里面视频删除
  /**
   * 删除小节
   *
   * @param id
   * @return
   */
  @DeleteMapping("/remove/{id}")
  public Result remove(@PathVariable String id) {
    videoService.removeById(id);
    return Result.ok();
  }
}
