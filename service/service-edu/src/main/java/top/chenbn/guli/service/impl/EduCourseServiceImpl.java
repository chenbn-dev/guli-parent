package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.EduCourseDescription;
import top.chenbn.guli.entity.vo.CourseInfoVO;
import top.chenbn.guli.exceptionhandler.GuliException;
import top.chenbn.guli.mapper.EduCourseMapper;
import top.chenbn.guli.service.EduCourseDescriptionService;
import top.chenbn.guli.service.EduCourseService;

/**
 * 课程 服务实现类
 *
 * @author chenbn
 * @since 2020-07-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
    implements EduCourseService {

  @Autowired private EduCourseDescriptionService courseDescriptionService;

  @Override
  public String saveCourseInfo(CourseInfoVO courseInfoVO) {
    // 1.向课程表添加课程基本信息
    EduCourse eduCourse = new EduCourse();
    BeanUtils.copyProperties(courseInfoVO, eduCourse);
    int rows = baseMapper.insert(eduCourse);
    if (rows == 0) {
      throw new GuliException(20001, "添加课程信息失败");
    }
    String cid = eduCourse.getId();
    // 2.向课程简介表添加课程简介
    EduCourseDescription courseDescription = new EduCourseDescription();
    courseDescription.setDescription(courseInfoVO.getDescription());
    // 设置描述id就是课程id
    courseDescription.setId(cid);
    courseDescriptionService.save(courseDescription);
    return cid;
  }

  @Override
  public CourseInfoVO getCourseInfo(String courseId) {
    // 1.查询课程表
    EduCourse eduCourse = baseMapper.selectById(courseId);
    CourseInfoVO courseInfoVO = new CourseInfoVO();
    BeanUtils.copyProperties(eduCourse, courseInfoVO);
    // 2.查询描述表
    EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
    courseInfoVO.setDescription(courseDescription.getDescription());
    return courseInfoVO;
  }

  @Override
  public void updateCourseInfo(CourseInfoVO courseInfoVO) {
    EduCourse eduCourse = new EduCourse();
    BeanUtils.copyProperties(courseInfoVO, eduCourse);
    int rows = baseMapper.updateById(eduCourse);
    if (rows == 0) {
      throw new GuliException(20001, "修改课程信息失败");
    }
    EduCourseDescription courseDescription = new EduCourseDescription();
    courseDescription.setId(courseInfoVO.getId());
    courseDescription.setDescription(courseInfoVO.getDescription());
    courseDescriptionService.updateById(courseDescription);
  }
}
