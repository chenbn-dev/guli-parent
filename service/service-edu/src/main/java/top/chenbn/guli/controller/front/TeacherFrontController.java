package top.chenbn.guli.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.EduTeacher;
import top.chenbn.guli.service.EduCourseService;
import top.chenbn.guli.service.EduTeacherService;

import java.util.List;
import java.util.Map;

/**
 * @author chbn
 * @create 2020-07-19 16:25
 */
@RestController
@RequestMapping("/edu/service/teacher-front")
// @CrossOrigin
public class TeacherFrontController {
  @Autowired private EduTeacherService teacherService;

  @Autowired private EduCourseService courseService;

  /**
   * 1 分页查询讲师的方法
   *
   * @param page
   * @param limit
   * @return
   */
  @PostMapping("/getTeacherFrontList/{page}/{limit}")
  public Result getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
    Page<EduTeacher> pageTeacher = new Page<>(page, limit);
    Map<String, Object> map = teacherService.getTeacherFrontList(pageTeacher);
    // 返回分页所有数据
    return Result.ok().data(map);
  }

  /**
   * 2 讲师详情的功能
   *
   * @param teacherId
   * @return
   */
  @GetMapping("/getTeacherFrontInfo/{teacherId}")
  public Result getTeacherFrontInfo(@PathVariable String teacherId) {
    // 1 根据讲师id查询讲师基本信息
    EduTeacher eduTeacher = teacherService.getById(teacherId);
    // 2 根据讲师id查询所讲课程
    QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
    wrapper.eq("teacher_id", teacherId);
    List<EduCourse> courseList = courseService.list(wrapper);
    return Result.ok().data("teacher", eduTeacher).data("courseList", courseList);
  }
}
