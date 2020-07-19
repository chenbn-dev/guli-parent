package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.frontvo.CourseFrontVo;
import top.chenbn.guli.entity.frontvo.CourseWebVo;
import top.chenbn.guli.entity.vo.CourseInfoVO;
import top.chenbn.guli.entity.vo.CoursePublishVO;

import java.util.Map;

/**
 * 课程 服务类
 *
 * @author chenbn
 * @since 2020-07-04
 */
public interface EduCourseService extends IService<EduCourse> {

  /**
   * 添加课程基本信息
   *
   * @param courseInfoVO
   * @return
   */
  String saveCourseInfo(CourseInfoVO courseInfoVO);
  /**
   * 根据id查询课程信息
   *
   * @param courseId
   * @return
   */
  CourseInfoVO getCourseInfo(String courseId);

  void updateCourseInfo(CourseInfoVO courseInfoVO);
  /**
   * 根据课程id查询课程确认信息
   *
   * @param id
   * @return
   */
  CoursePublishVO publishCourseInfo(String id);

  /**
   * 删除课程
   *
   * @param courseId
   */
  void removeCourse(String courseId);
  /**
   * 条件查询带分页查询课程
   *
   * @param page
   * @param limit
   * @param courseFrontVo
   * @return
   */
  Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);
  /**
   * 课程详情的方法
   *
   * @param courseId
   * @return
   */
  CourseWebVo getBaseCourseInfo(String courseId);
}
