package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.entity.EduChapter;
import top.chenbn.guli.entity.chapter.ChapterVO;
import top.chenbn.guli.service.EduChapterService;

import java.util.List;

/**
 * 课程 前端控制器
 *
 * @author chenbn
 * @since 2020-07-04
 */
@RestController
@RequestMapping("/edu/service/chapter")
@CrossOrigin
public class EduChapterController {
  @Autowired private EduChapterService chapterService;

  /**
   * 删除章节
   *
   * @param chapterId
   * @return
   */
  @DeleteMapping("/{chapterId}")
  public Result deleteChapter(@PathVariable String chapterId) {
    boolean flag = chapterService.deleteChapter(chapterId);
    if (flag) {
      return Result.ok();
    } else {
      return Result.error();
    }
  }

  /**
   * 修改章节
   *
   * @param eduChapter
   * @return
   */
  @PostMapping("/updateChapter")
  public Result updateChapter(@RequestBody EduChapter eduChapter) {
    chapterService.updateById(eduChapter);
    return Result.ok();
  }

  /**
   * 根据id查询章节
   *
   * @param chapterId
   * @return
   */
  @GetMapping("/getChapterInfo/{chapterId}")
  public Result getChapterInfo(@PathVariable String chapterId) {
    EduChapter eduChapter = chapterService.getById(chapterId);
    return Result.ok().data("chapter", eduChapter);
  }

  /**
   * 添加章节
   *
   * @param eduChapter
   * @return
   */
  @PostMapping("/addChapter")
  public Result addChapter(@RequestBody EduChapter eduChapter) {
    chapterService.save(eduChapter);
    return Result.ok();
  }

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
