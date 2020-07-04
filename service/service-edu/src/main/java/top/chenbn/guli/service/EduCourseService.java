package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.vo.CourseInfoVO;

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
}
