package top.chenbn.guli.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.common.util.ordervo.CourseWebVoOrder;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.chapter.ChapterVO;
import top.chenbn.guli.entity.frontvo.CourseFrontVo;
import top.chenbn.guli.entity.frontvo.CourseWebVo;
import top.chenbn.guli.service.EduChapterService;
import top.chenbn.guli.service.EduCourseService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu/service/coursefront")
@CrossOrigin
public class CourseFrontController {

  @Autowired private EduCourseService courseService;

  @Autowired private EduChapterService chapterService;

  /**
   * 1 条件查询带分页查询课程
   *
   * @param page
   * @param limit
   * @param courseFrontVo
   * @return
   */
  @PostMapping("/getFrontCourseList/{page}/{limit}")
  public Result getFrontCourseList(
      @PathVariable long page,
      @PathVariable long limit,
      @RequestBody(required = false) CourseFrontVo courseFrontVo) {
    Page<EduCourse> pageCourse = new Page<>(page, limit);
    Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);
    // 返回分页所有数据
    return Result.ok().data(map);
  }

  /**
   * 2 课程详情的方法
   *
   * @param courseId
   * @return
   */
  @GetMapping("/getFrontCourseInfo/{courseId}")
  public Result getFrontCourseInfo(@PathVariable String courseId) {
    // 根据课程id，编写sql语句查询课程信息
    CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
    // 根据课程id查询章节和小节
    List<ChapterVO> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
    return Result.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList);
  }

  // 根据课程id查询课程信息
  @PostMapping("/getCourseInfoOrder/{id}")
  public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
    CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
    CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
    BeanUtils.copyProperties(courseInfo, courseWebVoOrder);
    return courseWebVoOrder;
  }
}
