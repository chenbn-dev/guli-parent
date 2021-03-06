package top.chenbn.guli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.frontvo.CourseWebVo;
import top.chenbn.guli.entity.vo.CoursePublishVO;

/**
 * 课程 Mapper 接口
 *
 * @author chenbn
 * @since 2020-07-04
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
  CoursePublishVO getPublishCourseInfo(String courseId);

  //根据课程id，编写sql语句查询课程信息
  CourseWebVo getBaseCourseInfo(String courseId);
}
