package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.entity.chapter.ChapterVO;
import top.chenbn.guli.service.EduChapterService;

import java.util.List;

/**
 * 课程 前端控制器
 *
 * @author chenbn
 * @since 2020-07-04
 */
@CrossOrigin
@RestController
@RequestMapping("/guli/edu/chapter")
public class EduChapterController {
  @Autowired private EduChapterService chapterService;

  /**
   * 课程大纲列表
   *
   * @return
   */
  @GetMapping("/getChapterVideo/{courseId}")
  public Result getChapterVideo(@PathVariable String courseId) {
    List<ChapterVO> list = chapterService.getChapterVideoByCourseId(courseId);
    return Result.ok().data("allChapterVideo", list);
  }
}
