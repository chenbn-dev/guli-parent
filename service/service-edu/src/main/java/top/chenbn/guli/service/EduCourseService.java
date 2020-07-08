package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.vo.CourseInfoVO;
import top.chenbn.guli.entity.vo.CoursePublishVO;

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
}
