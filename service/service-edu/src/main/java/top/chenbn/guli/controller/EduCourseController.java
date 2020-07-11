package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.vo.CourseInfoVO;
import top.chenbn.guli.entity.vo.CoursePublishVO;
import top.chenbn.guli.service.EduCourseService;

import java.util.List;

/**
 * 课程 前端控制器
 *
 * @author chenbn
 * @since 2020-07-04
 */
@RestController
@RequestMapping("/edu/service/course")
@CrossOrigin
public class EduCourseController {

  @Autowired private EduCourseService courseService;
  // TODO 完成条件查询带分页

  @GetMapping
  public Result getCourseList() {
    List<EduCourse> list = courseService.list(null);
    return Result.ok().data("list", list);
  }
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

  /**
   * 根据课程id查询课程确认信息
   *
   * @param id
   * @return
   */
  @GetMapping("getPublishCourseInfo/{id}")
  public Result getPublishCourseInfo(@PathVariable String id) {
    CoursePublishVO coursePublishVo = courseService.publishCourseInfo(id);
    return Result.ok().data("publishCourse", coursePublishVo);
  }

  /**
   * 课程最终发布，修改课程状态
   *
   * @param id
   * @return
   */
  @PostMapping("/publishCourse/{id}")
  public Result publishCourse(@PathVariable String id) {
    EduCourse course = new EduCourse();
    course.setId(id);
    course.setStatus("Normal");
    courseService.updateById(course);
    return Result.ok();
  }

  /**
   * 删除课程
   *
   * @param courseId
   * @return
   */
  @DeleteMapping("/{courseId}")
  public Result deleteCourse(@PathVariable String courseId) {
    courseService.removeCourse(courseId);
    return Result.ok();
  }
}
