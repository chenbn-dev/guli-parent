package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.entity.vo.CourseInfoVO;
import top.chenbn.guli.service.EduCourseService;

/**
 * 课程 前端控制器
 *
 * @author chenbn
 * @since 2020-07-04
 */
@RestController
@RequestMapping("/guli/edu/course")
@CrossOrigin
public class EduCourseController {

  @Autowired private EduCourseService courseService;

  /**
   * 修改课程信息
   *
   * @param courseInfoVO
   * @return
   */
  @PostMapping("/updateCourseInfo")
  public Result updateCourseInfo(@RequestBody CourseInfoVO courseInfoVO) {
    courseService.updateCourseInfo(courseInfoVO);
    return Result.ok();
  }

  /**
   * 根据id查询课程信息
   *
   * @param courseId
   * @return
   */
  @GetMapping("/getCourseInfo/{courseId}")
  public Result getCourseInfo(@PathVariable String courseId) {
    CourseInfoVO courseInfoVO = courseService.getCourseInfo(courseId);
    return Result.ok().data("courseInfoVO", courseInfoVO);
  }

  /**
   * 添加课程的基本方法
   *
   * @param courseInfoVO
   * @return
   */
  @PostMapping("/addCourseInfo")
  public Result addCourseInfo(@RequestBody CourseInfoVO courseInfoVO) {
    // 返回添加课程之后的课程id，为了后面添加大岗使用
    String id = courseService.saveCourseInfo(courseInfoVO);
    return Result.ok().data("courseId", id);
  }
}